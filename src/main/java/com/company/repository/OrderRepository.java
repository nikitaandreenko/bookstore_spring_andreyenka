package com.company.repository;

import com.company.repository.entity.Order;

import java.util.List;

public interface OrderRepository extends AbstractRepository<Long, Order> {
    List<Order> findByUserId(Long userId);
}
