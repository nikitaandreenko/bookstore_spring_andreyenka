package com.company.data.repository;

import com.company.entity.Order;

import java.util.List;

public interface OrderRepository extends AbstractRepository<Long, Order> {
    List<Order> findByUserId(Long userId);

}
