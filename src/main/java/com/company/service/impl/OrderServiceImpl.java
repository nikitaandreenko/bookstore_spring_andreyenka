package com.company.service.impl;

import com.company.entity.Order;
import com.company.repository.OrderDao;
import com.company.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order create(Order entity) {
        return null;
    }

    @Override
    public Order findById(Long id) {
        Order order = orderDao.findById(id);
        return order;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = orderDao.findAll();
        return orders;
    }

    @Override
    public Long countAll() {
        return null;
    }

    @Override
    public Order update(Order entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Order> findByUserId(Long userId) {
        List<Order> orders = orderDao.findByUserId(userId);
        return orders;
    }
}
