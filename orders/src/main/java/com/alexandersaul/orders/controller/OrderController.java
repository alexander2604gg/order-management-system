package com.alexandersaul.orders.controller;

import com.alexandersaul.orders.constants.OrderConstants;
import com.alexandersaul.orders.dto.ResponseDTO;
import com.alexandersaul.orders.dto.order.OrderRequestDTO;
import com.alexandersaul.orders.dto.order.UpdateOrderStatusDTO;
import com.alexandersaul.orders.service.IOrderService;
import com.alexandersaul.orders.service.impl.OrderService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/updateStatus/{id}")
    public ResponseEntity<ResponseDTO> updateOrder (@PathVariable Long id , @RequestBody UpdateOrderStatusDTO updateOrderStatusDTO){
        orderService.updateOrderStatus(id , updateOrderStatusDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(OrderConstants.STATUS_200 , OrderConstants.MESSAGE_200));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteOrder (@PathVariable Long id) {
        boolean isDeleted = orderService.deleteOrder(id);
        if (isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(OrderConstants.STATUS_200,OrderConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(OrderConstants.STATUS_417, OrderConstants.MESSAGE_417_DELETE));
        }
    }


}
