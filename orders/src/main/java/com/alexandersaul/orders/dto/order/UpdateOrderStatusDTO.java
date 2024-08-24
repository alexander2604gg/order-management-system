package com.alexandersaul.orders.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UpdateOrderStatusDTO {
    private String status;
}
