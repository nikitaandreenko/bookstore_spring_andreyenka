package com.company.service.impl;

import com.company.entity.Order;
import com.company.data.repository.OrderRepository;
import com.company.service.OrderService;
import com.company.service.dto.ObjectMapperService;
import com.company.service.dto.OrderDtoService;
import lombok.experimental.Accessors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ObjectMapperService mapper;

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ObjectMapperService mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }


    @Override
    public Order create(OrderDtoService entity) {
        return null;
    }

    @Override
    public OrderDtoService findById(Long id) {
        log.debug("Get order by id={} from database orders", id);
        Order order = orderRepository.findById(id);
        OrderDtoService orderDtoService = mapper.toDto(order);
        return orderDtoService;
    }

    @Override
    public List<OrderDtoService> findAll() {
        log.debug("Get all orders from database orders");
        return orderRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public Long countAll() {
        return null;
    }

    @Override
    public Order update(OrderDtoService entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<OrderDtoService> findByUserId(Long userId) {
       return orderRepository.findByUserId(userId).stream().map(mapper::toDto).toList();
    }
}
