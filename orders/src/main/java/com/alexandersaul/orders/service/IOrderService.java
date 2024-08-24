package com.alexandersaul.orders.service;

import com.alexandersaul.orders.dto.order.OrderRequestDTO;
import com.alexandersaul.orders.dto.order.OrderResponseDTO;
import com.alexandersaul.orders.entity.Order;

import java.math.BigDecimal;
import java.util.List;

public interface IOrderService {

    void createOrder (OrderRequestDTO orderRequestDTO);
    void updateTotalAmount (Long idOrder , BigDecimal totalAmount);

}
