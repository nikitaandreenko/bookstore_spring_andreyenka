package com.company.service;

import com.company.service.dto.CartDto;
import com.company.service.dto.OrderDto;
import com.company.service.dto.UserDto;

import java.util.List;

public interface OrderService extends AbstractService<Long, OrderDto>{

    List<OrderDto> findByUserId(Long userId);

    OrderDto createOrderNew (List<CartDto> cartDto, UserDto userDto);

    OrderDto updateStatus (Long id, String status);
}
