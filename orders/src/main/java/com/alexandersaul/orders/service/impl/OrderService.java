package com.alexandersaul.orders.service.impl;

import com.alexandersaul.orders.dto.order.OrderRequestDTO;
import com.alexandersaul.orders.dto.order.OrderResponseDTO;
import com.alexandersaul.orders.dto.order.UpdateOrderStatusDTO;
import com.alexandersaul.orders.entity.Order;
import com.alexandersaul.orders.exception.ResourceNotFoundException;
import com.alexandersaul.orders.mapper.OrderMapper;
import com.alexandersaul.orders.repository.OrderRepository;
import com.alexandersaul.orders.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void createOrder(OrderRequestDTO orderRequestDTO) {
        Order order = orderMapper.toEntity(orderRequestDTO);
        order.setCreatedBy("Alexander Saul");
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("Pending");
        orderRepository.save(order);
    }

    @Override
    public void updateTotalAmount(Long orderId, BigDecimal totalAmount) {

        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setTotalAmount(totalAmount);
            orderRepository.save(order);
        }
    }

    @Override
    public void updateOrderStatus(Long id , UpdateOrderStatusDTO orderUpdateStatusDTO) {

        Optional<Order> orderOptional = orderRepository.findById(id);

        if (orderOptional.isPresent()){
            Order order = orderOptional.get();
            order.setStatus(orderUpdateStatusDTO.getStatus());
            orderRepository.save(order);
        }
    }

    @Override
    public OrderResponseDTO findById(Long id) {
        Optional<Order> orderOptional  = orderRepository.findById(id);
        if (orderOptional.isEmpty()){
            throw new ResourceNotFoundException("Order","orderId",String.valueOf(id));
        }
        return orderMapper.toDTO(orderOptional.get());
    }

    @Override
    public boolean deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Order" , "orderId" , String.valueOf(id))
        );
        orderRepository.deleteById(order.getOrderId());
        return true;
    }
}
