package com.alexandersaul.orders.service;

import com.alexandersaul.orders.dto.order.OrderBoletaDTO;
import com.alexandersaul.orders.dto.order.OrderRequestDTO;
import com.alexandersaul.orders.dto.order.OrderResponseDTO;
import com.alexandersaul.orders.dto.order.UpdateOrderStatusDTO;
import com.alexandersaul.orders.dto.orderdetail.OrderDetailRequestDTO;
import com.alexandersaul.orders.dto.orderdetail.OrderDetailResponseDTO;
import com.alexandersaul.orders.entity.Order;
import java.math.BigDecimal;
import java.util.List;


public interface IOrderService {

    List<OrderResponseDTO> getAllOrders ();
    List<OrderDetailResponseDTO> getOrderDetails(Long productId);
    OrderResponseDTO findOrderById (Long productId);
    OrderResponseDTO createOrder (OrderRequestDTO orderRequestDTO);
    OrderBoletaDTO createBoleta (Long orderId);
    void addOrderDetail (Long orderId, List<OrderDetailRequestDTO> orderDetailRequestDTOList);
    void updateOrderStatus ( Long id , UpdateOrderStatusDTO orderUpdateStatusDTO);
    OrderResponseDTO findById (Long id);
    void deleteOrder (Long id);
}
