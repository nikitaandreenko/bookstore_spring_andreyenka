package com.company.repository;

import com.company.entity.Order;
import com.company.entity.OrderItem;

import java.util.List;

public interface OrderDao extends AbstractDao<Long, Order> {
    List<Order> findByUserId(Long userId);

}
