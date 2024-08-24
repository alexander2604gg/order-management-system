package com.alexandersaul.orders.service;

import com.alexandersaul.orders.dto.order.OrderRequestDTO;
import com.alexandersaul.orders.dto.order.UpdateOrderStatusDTO;

import java.math.BigDecimal;

public interface IOrderService {

    void createOrder (OrderRequestDTO orderRequestDTO);
    void updateTotalAmount (Long idOrder , BigDecimal totalAmount);
    void updateOrderStatus ( Long id , UpdateOrderStatusDTO orderUpdateStatusDTO);

}
