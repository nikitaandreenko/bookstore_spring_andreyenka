package com.company.data.dao;

import com.company.data.dto.OrderItemDto;

import java.util.List;

public interface OrderItemDao extends AbstractDao<Long, OrderItemDto>{
    List<OrderItemDto> findByOrderId(Long orderId);
}
