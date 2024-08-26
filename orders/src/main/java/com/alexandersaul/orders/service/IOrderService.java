package com.alexandersaul.orders.service;

import com.alexandersaul.orders.dto.order.OrderRequestDTO;
import com.alexandersaul.orders.dto.order.OrderResponseDTO;
import com.alexandersaul.orders.dto.order.UpdateOrderStatusDTO;

import java.math.BigDecimal;
import java.util.Optional;

public interface IOrderService {

    void createOrder (OrderRequestDTO orderRequestDTO);
    void updateTotalAmount (Long idOrder , BigDecimal totalAmount);
    void updateOrderStatus ( Long id , UpdateOrderStatusDTO orderUpdateStatusDTO);
    OrderResponseDTO findById (Long id);
    boolean deleteOrder (Long id);
}
