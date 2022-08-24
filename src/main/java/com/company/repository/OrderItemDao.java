package com.company.repository;

import com.company.entity.Order;
import com.company.entity.OrderItem;

import java.util.List;

public interface OrderItemDao extends AbstractDao<Long, OrderItem>{

    List <OrderItem> findByOrderId(Long orderId);

}
