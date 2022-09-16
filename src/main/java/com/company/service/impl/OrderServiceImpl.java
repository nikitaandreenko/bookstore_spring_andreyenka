package com.company.service.impl;

import com.company.repository.entity.Book;
import com.company.repository.entity.Order;
import com.company.repository.OrderRepository;
import com.company.repository.entity.OrderItem;
import com.company.repository.entity.User;
import com.company.service.OrderService;
import com.company.service.dto.*;
import com.company.service.exception.BadRequestException;
import com.company.service.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service("orderService")
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ObjectMapperService mapper;

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public OrderDto create(OrderDto orderDto) {
        log.debug("Create order={} in database order", orderDto);
        Order orderCreated = mapper.toEntity(orderDto);
        orderRepository.create(orderCreated);
        return mapper.toDto(orderCreated);
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
        List<Order> orders = orderRepository.findAll();
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
        List<Order> orders = orderRepository.findByUserId(userId);
        if (orders.isEmpty()) {
            throw new EntityNotFoundException("Orders with user id:" + userId + " doesn't exist");
        }
        return orderRepository.findByUserId(userId).stream().map(mapper::toDto).toList();
    }

    @Override
    public OrderDto createOrderNew(List<CartDto> cartDto, UserDto userDto) {
        OrderDto orderDto = new OrderDto();
        BigDecimal sum = BigDecimal.ZERO;
        for (CartDto dto : cartDto) {
            sum = sum.add(dto.getBookDto().getPrice().multiply(BigDecimal.valueOf(dto.getQuantity())));
        }
        List <OrderItemDto> orderItemDtos = cartDto.stream().map(cartDto1 -> {
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setBook(cartDto1.getBookDto());
            orderItemDto.setPrice(cartDto1.getBookDto().getPrice());
            orderItemDto.setQuantity(cartDto1.getQuantity());
            return orderItemDto;
        }).collect(Collectors.toList());
        orderDto.setStatus(OrderDto.Status.PENDING);
        orderDto.setTotalCost(sum);
        orderDto.setUser(userDto);
        orderDto.setItems(orderItemDtos);
        Order order = mapper.toEntity(orderDto);
        orderRepository.create(order);
        return mapper.toDto(order);
    }
}
