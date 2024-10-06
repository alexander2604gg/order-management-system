package com.alexandersaul.orders.controller;

import com.alexandersaul.orders.constants.OrderConstants;
import com.alexandersaul.orders.dto.ResponseDTO;
import com.alexandersaul.orders.dto.order.OrderRequestDTO;
import com.alexandersaul.orders.dto.order.OrderResponseDTO;
import com.alexandersaul.orders.dto.order.UpdateOrderStatusDTO;
import com.alexandersaul.orders.dto.orderdetail.OrderDetailRequestDTO;
import com.alexandersaul.orders.dto.orderdetail.OrderDetailResponseDTO;
import com.alexandersaul.orders.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders () {
        List<OrderResponseDTO> orderResponseDTOS = orderService.getAllOrders();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderResponseDTOS);
    }

    @GetMapping("/findOrder/{orderId}")
    public ResponseEntity<OrderResponseDTO> findOrder (@PathVariable Long orderId) {
        OrderResponseDTO orderResponseDTO = orderService.findOrderById(orderId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderResponseDTO);
    }

    @GetMapping("/findDetails")
    public ResponseEntity<List<OrderDetailResponseDTO>> getAllDetails (@RequestParam Long orderId) {
        List<OrderDetailResponseDTO> orderDetails = orderService.getOrderDetails(orderId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderDetails);
    }
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO orderResponseDTO = orderService.createOrder(orderRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderResponseDTO);
    }

    @PostMapping("/{orderId}/details")
    public ResponseEntity<ResponseDTO> addOrderDetails(@PathVariable Long orderId, @RequestBody List<OrderDetailRequestDTO> orderDetailsRequest) {
        orderService.addOrderDetail(orderId, orderDetailsRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ResponseDTO> updateOrderStatus(@PathVariable Long id, @RequestBody UpdateOrderStatusDTO updateOrderStatusDTO) {
        orderService.updateOrderStatus(id, updateOrderStatusDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(OrderConstants.STATUS_200, OrderConstants.MESSAGE_200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(OrderConstants.STATUS_200, OrderConstants.MESSAGE_200));
    }
}

