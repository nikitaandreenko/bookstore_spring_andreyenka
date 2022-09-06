package com.company.service;

import com.company.service.dto.OrderDto;

import java.util.List;

public interface OrderService extends AbstractService<Long, OrderDto>{

    List<OrderDto> findByUserId(Long userId);

}
