package com.alexandersaul.orders.service.impl;

import com.alexandersaul.orders.constants.OrderStatus;
import com.alexandersaul.orders.dto.InventoryMovementRequestDTO;
import com.alexandersaul.orders.dto.ResponseDTO;
import com.alexandersaul.orders.dto.inventory.InventoryResponseDTO;
import com.alexandersaul.orders.dto.order.OrderResponseDTO;
import com.alexandersaul.orders.dto.orderdetail.OrderDetailRequestDTO;
import com.alexandersaul.orders.entity.Order;
import com.alexandersaul.orders.entity.OrderDetail;
import com.alexandersaul.orders.exception.NotReturnedDataException;
import com.alexandersaul.orders.exception.OrderDetailNotAllowedToDeleteException;
import com.alexandersaul.orders.exception.ResourceNotFoundException;
import com.alexandersaul.orders.exception.StockNotEnoughException;
import com.alexandersaul.orders.mapper.OrderDetailMapper;
import com.alexandersaul.orders.repository.OrderDetailRepository;
import com.alexandersaul.orders.service.IOrderDetailService;
import com.alexandersaul.orders.service.client.InventoryFeignClient;
import com.alexandersaul.orders.service.client.InventoryMovementFeignClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderDetailService implements IOrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private InventoryFeignClient inventoryFeignClient;
    @Autowired
    private InventoryMovementFeignClient inventoryMovementFeignClient;


    @Override
    public void createOrderDetails(Order order , List<OrderDetailRequestDTO> orderDetailRequestDTOList) {

        if (orderDetailRequestDTOList == null || orderDetailRequestDTOList.isEmpty()) {
            throw new IllegalArgumentException("Detail order list can not be null.");
        }

        List<OrderDetail> orderDetailList = orderDetailMapper.toEntityList(orderDetailRequestDTOList);

        for (OrderDetail orderDetail : orderDetailList){
            orderDetail.setOrder(order);
        }

        orderDetailList.forEach(orderDetail -> {
            orderDetail.setCreatedAt(LocalDateTime.now());
            orderDetail.setCreatedBy("Alexander");
        });
        orderDetailRepository.saveAll(orderDetailList);

    }

    @Override
    @Transactional
    public void deleteOrderDetail(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("OrderDetail", "orderId", String.valueOf(id))
        );
        if (orderDetail.getOrder().getStatus().equals(OrderStatus.PENDING.name())) {
            orderDetailRepository.delete(orderDetail);
        } else{
            throw new OrderDetailNotAllowedToDeleteException(orderDetail.getOrder().getOrderId(),orderDetail.getOrder().getStatus());
        }
    }

    @Override
    public boolean processOrderPayment(List<OrderDetail> orderDetails) {
        List<InventoryResponseDTO> inventoryResponseDTOS = getInventoryData(orderDetails);
        List<InventoryMovementRequestDTO> inventoryMovements = createInventoryMovements(orderDetails, inventoryResponseDTOS);
        return updateInventory(inventoryMovements);
    }

    private List<InventoryResponseDTO> getInventoryData(List<OrderDetail> orderDetails) {
        ResponseEntity<List<InventoryResponseDTO>> response = inventoryFeignClient.findInventoryByProductIds(
                orderDetails.stream().map(OrderDetail::getProductId).toList()
        );
        System.out.println(response);
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new NotReturnedDataException("Inventory response ");
        }

        return response.getBody();
    }

    private boolean updateInventory(List<InventoryMovementRequestDTO> inventoryMovements) {
        System.out.println(inventoryMovements);
        ResponseEntity<ResponseDTO> response = inventoryMovementFeignClient.createInventoryMovements(inventoryMovements);

        System.out.println(response);
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new NotReturnedDataException("Inventory response ");
        }

        return response.getStatusCode().is2xxSuccessful();
    }

    private List<InventoryMovementRequestDTO> createInventoryMovements(List<OrderDetail> orderDetails, List<InventoryResponseDTO> inventoryData) {
        List<InventoryMovementRequestDTO> inventoryMovements = new ArrayList<>();

        for (InventoryResponseDTO inventory : inventoryData) {
            Long productId = inventory.getProductId();
            int requestedQuantity = getRequestedQuantity(orderDetails, productId);

            if (requestedQuantity > inventory.getQuantity()) {
                throw new StockNotEnoughException(productId, inventory.getQuantity());
            }

            InventoryMovementRequestDTO movementRequest = InventoryMovementRequestDTO.builder()
                    .productId(productId)
                    .movementType("INCOMING")
                    .quantity(requestedQuantity)
                    .reason("Sell")
                    .build();

            inventoryMovements.add(movementRequest);
        }

        return inventoryMovements;
    }

    private int getRequestedQuantity(List<OrderDetail> orderDetails, Long productId) {
        return orderDetails.stream()
                .filter(od -> Objects.equals(od.getProductId(), productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Order detail" , "productId" , productId.toString()))
                .getQuantity();
    }


}
