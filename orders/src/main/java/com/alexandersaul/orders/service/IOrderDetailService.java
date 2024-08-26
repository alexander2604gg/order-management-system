package com.alexandersaul.orders.service;

import com.alexandersaul.orders.dto.orderdetail.OrderDetailRequestDTO;

import java.util.List;

public interface IOrderDetailService {

    void createOrderDetails (List<OrderDetailRequestDTO> orderDetailRequestDTOList);
    boolean deleteOrderDetail (Long id);


}
