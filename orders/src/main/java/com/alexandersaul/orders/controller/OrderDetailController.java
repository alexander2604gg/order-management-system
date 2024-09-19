package com.alexandersaul.orders.controller;

import com.alexandersaul.orders.constants.OrderConstants;
import com.alexandersaul.orders.dto.ResponseDTO;
import com.alexandersaul.orders.service.impl.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/api/orderDetail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteOrderDetail (@PathVariable Long id) {
        orderDetailService.deleteOrderDetail(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(OrderConstants.STATUS_200 , OrderConstants.MESSAGE_200));
    }
}


