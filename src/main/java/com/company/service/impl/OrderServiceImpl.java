package com.company.service.impl;

import com.company.repository.entity.Order;
import com.company.repository.OrderRepository;
import com.company.service.OrderService;
import com.company.service.dto.ObjectMapperService;
import com.company.service.dto.OrderDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
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
    public OrderDto create(OrderDto entity) {
        return null;
    }

    @Override
    public OrderDto findById(Long id) {
        log.debug("Get order by id={} from database orders", id);
        Order order = orderRepository.findById(id);
        OrderDto orderDto = mapper.toDto(order);
        return orderDto;
    }

    @Override
    public List<OrderDto> findAll() {
        log.debug("Get all orders from database orders");
        List <Order> orders = orderRepository.findAll();
        return orders.stream().map(mapper::toDto).toList();
    }

    @Override
    public OrderDto update(OrderDto entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<OrderDto> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId).stream().map(mapper::toDto).toList();
    }
}
