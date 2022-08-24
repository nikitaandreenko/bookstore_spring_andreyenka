package com.company.repository.impl;

import com.company.entity.Book;
import com.company.entity.OrderItem;
import com.company.repository.BookDao;
import com.company.repository.OrderItemDao;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
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

    private static final Logger log = LogManager.getLogger(BookDaoImpl.class);

    public static final String GET_BY_ID = "SELECT * FROM order_items WHERE id = ?";
    public static final String GET_ALL = "SSELECT * FROM order_items ";
    public static final String GET_BY_ORDER_ID = "SELECT * FROM order_items WHERE order_id = ?";


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
    public OrderItem create(OrderItem orderItem) {
        return null;
    }

    @Override
    public OrderItem findById(Long id) {
        log.debug("Get orderItems by id={} from database order_items", id);
        try {
            return jdbcTemplate.queryForObject(GET_BY_ID, this::processRow, id);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public List<OrderItem> findAll() {

        log.debug("Get all orderItems from database order_items");
        return jdbcTemplate.query(GET_ALL, this::processRow);
    }

    @Override
    public Long countAll() {
        return null;
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<OrderItem> findByOrderId(Long orderId) {

        log.debug("Get orderItems by order_id={} from database order_items", orderId);
        try {
            return jdbcTemplate.query(GET_BY_ORDER_ID, this::processRow, orderId);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }
}
