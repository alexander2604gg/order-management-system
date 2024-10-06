package com.alexandersaul.orders.repository;

import com.alexandersaul.orders.entity.Order;
import com.alexandersaul.orders.entity.OrderDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDetailRepository extends CrudRepository<OrderDetail,Long> {
    List<OrderDetail> findByOrder(Order order);
}
