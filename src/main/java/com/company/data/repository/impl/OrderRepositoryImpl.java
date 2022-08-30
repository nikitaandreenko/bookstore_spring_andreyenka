package com.company.data.repository.impl;

import com.company.data.dao.BookDao;
import com.company.data.dao.OrderDao;
import com.company.data.dao.OrderItemDao;
import com.company.data.dao.UserDao;
import com.company.data.dto.BookDto;
import com.company.data.dto.ObjectMapper;
import com.company.data.dto.OrderDto;
import com.company.data.dto.UserDto;
import com.company.data.repository.OrderRepository;

import com.company.entity.Book;
import com.company.entity.Order;
import com.company.entity.OrderItem;
import com.company.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("orderRepository")
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderDao orderDao;
    private final UserDao userDao;
    private final BookDao bookDao;
    private final OrderItemDao orderItemDao;
    private final ObjectMapper mapper;

    @Autowired
    public OrderRepositoryImpl(OrderDao orderDao, UserDao userDao, BookDao bookDao, OrderItemDao orderItemDao, ObjectMapper mapper) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.bookDao = bookDao;
        this.orderItemDao = orderItemDao;
        this.mapper = mapper;
    }

    @Override
    public Order create(Order entity) {
        return null;
    }

    @Override
    public Order findById(Long id) {
        OrderDto orderDto = orderDao.findById(id);
        if (orderDto == null) {
            return null;
        }
        Order orderEntity = mapper.toEntity(orderDto);
        Long userId = orderDto.getUserId();
        UserDto userDto = userDao.findById(userId);
        User user = mapper.toEntity(userDto);
        orderEntity.setUser(user);
        List<OrderItem> items = orderItemDao.findByOrderId(orderDto.getId()).stream()
                .map(dto -> {
                    OrderItem entity = mapper.toEntity(dto);
                    entity.setId(orderEntity.getId());
                    Long bookId = dto.getBookId();
                    BookDto bookDto = bookDao.findById(bookId);
                    Book book = mapper.toEntity(bookDto);
                    entity.setBook(book);
                    return entity;
                }).toList();
        orderEntity.setItems(items);
        return orderEntity;
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll().stream()
                .map(orderDto -> {
                    Order orderEntity = mapper.toEntity(orderDto);
                    Long userId = orderDto.getUserId();
                    UserDto userDto = userDao.findById(userId);
                    User user = mapper.toEntity(userDto);
                    orderEntity.setUser(user);
                    List<OrderItem> items = orderItemDao.findByOrderId(orderDto.getId()).stream()
                            .map(dto -> {
                                OrderItem entity = mapper.toEntity(dto);
                                entity.setId(orderEntity.getId());
                                Long bookId = dto.getBookId();
                                BookDto bookDto = bookDao.findById(bookId);
                                Book book = mapper.toEntity(bookDto);
                                entity.setBook(book);
                                return entity;
                            }).toList();
                    orderEntity.setItems(items);
                    return orderEntity;
                }).toList();
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
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return orderDao.findByUserId(userId).stream()
                .map(orderDto -> {
                    Order orderEntity = mapper.toEntity(orderDto);
                    Long userIdOrder = orderDto.getUserId();
                    UserDto userDto = userDao.findById(userId);
                    User user = mapper.toEntity(userDto);
                    orderEntity.setUser(user);
                    List<OrderItem> items = orderItemDao.findByOrderId(orderDto.getId()).stream()
                            .map(dto -> {
                                OrderItem entity = mapper.toEntity(dto);
                                entity.setId(orderEntity.getId());
                                Long bookId = dto.getBookId();
                                BookDto bookDto = bookDao.findById(bookId);
                                Book book = mapper.toEntity(bookDto);
                                entity.setBook(book);
                                return entity;
                            }).toList();
                    orderEntity.setItems(items);
                    return orderEntity;
                }).toList();
    }
}
