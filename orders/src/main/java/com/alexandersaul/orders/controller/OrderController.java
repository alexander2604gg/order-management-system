package com.alexandersaul.orders.controller;

import com.alexandersaul.orders.constants.OrderConstants;
import com.alexandersaul.orders.dto.ResponseDTO;
import com.alexandersaul.orders.dto.order.OrderRequestDTO;
import com.alexandersaul.orders.service.IOrderService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createOrder (@RequestBody OrderRequestDTO orderRequestDTO){
        orderService.createOrder(orderRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(OrderConstants.STATUS_201 , OrderConstants.MESSAGE_201));
    }



}
