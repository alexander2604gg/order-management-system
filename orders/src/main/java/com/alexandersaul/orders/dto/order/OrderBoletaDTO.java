package com.alexandersaul.orders.dto.order;

import com.alexandersaul.orders.dto.orderdetail.OrderDetailResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderBoletaDTO {

    OrderResponseDTO orderResponseDTO;
    List<OrderDetailResponseDTO> orderDetailResponseDTOList;

}
