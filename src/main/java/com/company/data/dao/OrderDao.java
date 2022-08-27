package com.company.data.dao;

import com.company.data.dto.OrderDto;

import java.util.List;

public interface OrderDao extends AbstractDao<Long, OrderDto>{
    List<OrderDto> findByUserId(Long userId);
}
