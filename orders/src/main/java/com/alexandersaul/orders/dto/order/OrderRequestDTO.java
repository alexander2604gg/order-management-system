package com.alexandersaul.orders.dto.order;


import com.alexandersaul.orders.dto.orderdetail.OrderDetailRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class OrderRequestDTO {
    private String userId;
    private List<OrderDetailRequestDTO> details;
}
