package com.company.service.impl;

import com.company.repository.entity.Order;
import com.company.repository.OrderRepository;
import com.company.service.OrderService;
import com.company.service.dto.ObjectMapperService;
import com.company.service.dto.OrderDto;
import com.company.service.exception.BadRequestException;
import com.company.service.exception.EntityNotFoundException;
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
        if (order == null) {
            throw new EntityNotFoundException("User with id:" + id + " doesn't exist");
        }
        return mapper.toDto(order);
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
        log.debug("Get orders from database orders by userID");
        List <Order> orders = orderRepository.findByUserId(userId);
        if (orders.isEmpty()) {
            throw new EntityNotFoundException("Orders with user id:" + userId + " doesn't exist");
        }
        return orderRepository.findByUserId(userId).stream().map(mapper::toDto).toList();
    }
}
