package com.alexandersaul.orders.repository;

import com.alexandersaul.orders.entity.OrderDetail;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailRepository extends CrudRepository<OrderDetail,Long> {
}
