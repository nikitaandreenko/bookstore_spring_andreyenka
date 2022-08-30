package com.company.service;

import com.company.entity.Order;
import com.company.service.dto.OrderDtoService;

import java.util.List;

public interface OrderService extends AbstractService<Long, Order, OrderDtoService>{

    List<OrderDtoService> findByUserId(Long userId);

}
