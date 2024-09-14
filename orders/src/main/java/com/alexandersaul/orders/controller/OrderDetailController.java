package com.alexandersaul.orders.controller;

import com.alexandersaul.orders.constants.OrderConstants;
import com.alexandersaul.orders.constants.OrderDetailConstants;
import com.alexandersaul.orders.dto.ResponseDTO;
import com.alexandersaul.orders.dto.orderdetail.OrderDetailRequestDTO;
import com.alexandersaul.orders.service.impl.OrderDetailService;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteOrderDetail (@PathVariable Long id) {
        boolean isDelete = orderDetailService.deleteOrderDetail(id);
        if (isDelete){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(OrderConstants.STATUS_200 , OrderConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(OrderConstants.STATUS_500, OrderConstants.MESSAGE_500_DELETE));
        }

    }


}
