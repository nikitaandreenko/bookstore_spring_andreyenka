package com.company.service.impl;

import com.company.repository.entity.Order;
import com.company.repository.OrderRepository;

import com.company.service.OrderService;
import com.company.service.dto.*;

import com.company.service.exception.EntityNotFoundException;
import com.company.service.exception.ValidateException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

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
        log.debug("Delete order by id={} from database orders", id);
        if (!orderRepository.delete(id)) {
            throw new EntityNotFoundException("No entity with id: " + id);
        }
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
        List<OrderItemDto> orderItemDtos = cartDto.stream().map(cartDto1 -> {
            OrderItemDto orderItemDto = new OrderItemDto();
            BookDto bookDto = cartDto1.getBookDto();
            if (bookDto.getAvailability().equals("in stock")) {
                orderItemDto.setBook(bookDto);
            }
            orderItemDto.setPrice(cartDto1.getBookDto().getPrice());
            orderItemDto.setQuantity(cartDto1.getQuantity());
            return orderItemDto;
        }).collect(Collectors.toList());
        orderDto.setStatus(OrderDto.Status.PENDING);
        orderDto.setTotalCost(sum);
        orderDto.setUser(userDto);
        orderDto.setItems(orderItemDtos);
        Order order = mapper.toEntity(orderDto);
        Order orderCreated = orderRepository.create(order);
        if (orderCreated == null) {
            throw new EntityNotFoundException("Order didn't create...");
        }
        return mapper.toDto(order);
    }

    @Override
    public OrderDto updateStatus(Long id, String status) {
        log.debug("Update status order={} in database books", id);
        Order order = orderRepository.findById(id);
        order.setStatus(Order.Status.valueOf(status));
        Order updatedOrder = orderRepository.update(order);
        if (updatedOrder == null) {
            throw new EntityNotFoundException("Order didn't update...");
        }
        return mapper.toDto(updatedOrder);
    }
}
