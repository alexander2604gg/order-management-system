package com.alexandersaul.orders.service;

import com.alexandersaul.orders.dto.orderdetail.OrderDetailRequestDTO;
import com.alexandersaul.orders.entity.Order;
import com.alexandersaul.orders.entity.OrderDetail;

import java.util.List;

public interface IOrderDetailService {

    void createOrderDetails (Order order , List<OrderDetailRequestDTO> orderDetailRequestDTOList);
    boolean processOrderPayment (List<OrderDetail> orderDetails);
    void deleteOrderDetail (Long id);

}
