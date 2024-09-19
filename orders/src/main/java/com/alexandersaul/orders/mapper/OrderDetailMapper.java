package com.alexandersaul.orders.mapper;

import com.alexandersaul.orders.dto.orderdetail.OrderDetailRequestDTO;
import com.alexandersaul.orders.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "orderDetailId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    OrderDetail toEntity (OrderDetailRequestDTO orderDetailRequestDTO);
    List<OrderDetail> toEntityList (List<OrderDetailRequestDTO> orderDetailRequestDTOList);

}
