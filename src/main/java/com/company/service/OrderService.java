package com.company.service;

import com.company.entity.Order;

import java.util.List;

public interface OrderService extends AbstractService<Long, Order>{

    public List<Order> findByUserId(Long userId);

}
