package com.alexandersaul.orders.service.impl;

import com.alexandersaul.orders.dto.order.OrderResponseDTO;
import com.alexandersaul.orders.dto.orderdetail.OrderDetailRequestDTO;
import com.alexandersaul.orders.entity.Order;
import com.alexandersaul.orders.entity.OrderDetail;
import com.alexandersaul.orders.exception.ResourceNotFoundException;
import com.alexandersaul.orders.mapper.OrderDetailMapper;
import com.alexandersaul.orders.repository.OrderDetailRepository;
import com.alexandersaul.orders.repository.OrderRepository;
import com.alexandersaul.orders.service.IOrderDetailService;
import com.fasterxml.jackson.core.io.BigDecimalParser;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderDetailService implements IOrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private OrderService orderService;

    @Override
    public void createOrderDetails(List<OrderDetailRequestDTO> orderDetailRequestDTOList) {

        if (orderDetailRequestDTOList == null || orderDetailRequestDTOList.isEmpty()) {
            throw new IllegalArgumentException("Detail order list can not be null.");
        }

        List<OrderDetail> orderDetailList = orderDetailMapper.toEntityList(orderDetailRequestDTOList);
        Long orderId = orderDetailList.get(0).getOrder().getOrderId();

        for (OrderDetail detail : orderDetailList) {
            if (detail.getOrder() == null || !orderId.equals(detail.getOrder().getOrderId())) {
                throw new IllegalArgumentException("All the details should have the same order id.");
            }
        }

        OrderResponseDTO orderResponseDTO = orderService.findById(orderId);

        if (orderResponseDTO != null) {
            orderDetailRepository.saveAll(orderDetailList);
            BigDecimal totalAmount = calculateTotalAmount(orderDetailList);
            orderService.updateTotalAmount(orderId, totalAmount);
        }
    }



    public BigDecimal calculateTotalAmount (List<OrderDetail> orderDetailList) {
        return orderDetailList.stream()
                .map(orderDetail -> orderDetail.getPricePerUnit().multiply(
                        new BigDecimal(orderDetail.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
