package com.alexandersaul.orders.controller;

import com.alexandersaul.orders.constants.OrderDetailConstants;
import com.alexandersaul.orders.dto.ResponseDTO;
import com.alexandersaul.orders.dto.orderdetail.OrderDetailRequestDTO;
import com.alexandersaul.orders.service.impl.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/api/orderDetail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createOrderDetails (@RequestBody List<OrderDetailRequestDTO> orderDetailRequestDTO){
        orderDetailService.createOrderDetails(orderDetailRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(OrderDetailConstants.STATUS_201 , OrderDetailConstants.MESSAGE_201));
    }

}
