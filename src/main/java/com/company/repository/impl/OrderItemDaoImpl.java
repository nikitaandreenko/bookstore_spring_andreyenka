package com.company.repository.impl;

import com.company.entity.Book;
import com.company.entity.Order;
import com.company.entity.OrderItem;
import com.company.entity.User;
import com.company.repository.BookDao;
import com.company.repository.OrderItemDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderItemDaoImpl implements OrderItemDao {

    private final JdbcTemplate jdbcTemplate;
    private final BookDao bookDao;

    private OrderItem processRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(rs.getLong("id"));
        Long bookId = rs.getLong("book_id");
        Book book = bookDao.findById(bookId);
        orderItem.setBook(book);
        orderItem.setQuantity(rs.getInt("quantity"));
        orderItem.setPrice(rs.getBigDecimal("price"));
        return orderItem;
    }

    @Override
    public Order create(Order entity) {
        return null;
    }

    @Override
    public Order findById(Long id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
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
    public List<OrderItem> findByOrderId(Long OrderId) {
        return null;
    }
}
