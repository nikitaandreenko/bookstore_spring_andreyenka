package com.company.repository;

import com.company.repository.entity.Order;
import com.company.repository.entity.OrderItem;
import com.company.service.dto.OrderDto;
import com.company.service.dto.OrderItemDto;

import java.util.List;

public interface OrderRepository extends AbstractRepository<Long, Order> {
    List<Order> findByUserId(Long userId);

}
