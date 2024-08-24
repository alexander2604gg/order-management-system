package com.alexandersaul.orders.mapper;

import com.alexandersaul.orders.dto.order.OrderRequestDTO;
import com.alexandersaul.orders.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
    Order toEntity (OrderRequestDTO orderRequestDTO);

}
