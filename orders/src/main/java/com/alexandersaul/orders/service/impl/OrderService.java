package com.alexandersaul.orders.service.impl;

import com.alexandersaul.orders.dto.order.OrderRequestDTO;
import com.alexandersaul.orders.dto.order.OrderResponseDTO;
import com.alexandersaul.orders.entity.Order;
import com.alexandersaul.orders.mapper.OrderMapper;
import com.alexandersaul.orders.repository.OrderRepository;
import com.alexandersaul.orders.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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
}
