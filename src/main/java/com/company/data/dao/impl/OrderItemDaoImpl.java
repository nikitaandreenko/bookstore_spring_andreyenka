package com.company.data.dao.impl;

import com.company.data.dao.OrderItemDao;
import com.company.data.dto.OrderItemDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class OrderItemDaoImpl implements OrderItemDao {
    private final JdbcTemplate jdbcTemplate;

    private static final Logger log = LogManager.getLogger(OrderItemDaoImpl.class);

    @Autowired
    public OrderItemDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static final String GET_BY_ID = "SELECT order_items.id, order_items.order_id, order_items.book_id, order_items.quantity, order_items.price FROM order_items WHERE id = ?";
    public static final String GET_ALL = "SELECT order_items.id,order_items.order_id, order_items.book_id, order_items.quantity, order_items.price FROM order_items ";
    public static final String GET_BY_ORDER_ID = "SELECT order_items.id, order_items.order_id, order_items.book_id, order_items.quantity, order_items.price FROM order_items WHERE order_id = ?";


    private OrderItemDto processRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(rs.getLong("id"));
        orderItemDto.setBookId(rs.getLong("book_id"));
        orderItemDto.setOrderId(rs.getLong("order_id"));
        orderItemDto.setQuantity(rs.getInt("quantity"));
        orderItemDto.setPrice(rs.getBigDecimal("price"));
        return orderItemDto;
    }

    @Override
    public OrderItemDto create(OrderItemDto orderItemDto) {
        return null;
    }

    @Override
    public OrderItemDto findById(Long id) {
        log.debug("Get orderItems by id={} from database order_items", id);
        try {
            return jdbcTemplate.queryForObject(GET_BY_ID, this::processRow, id);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public List<OrderItemDto> findAll() {

        log.debug("Get all orderItems from database order_items");
        return jdbcTemplate.query(GET_ALL, this::processRow);
    }

    @Override
    public Long countAll() {
        return null;
    }

    @Override
    public OrderItemDto update(OrderItemDto entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<OrderItemDto> findByOrderId(Long orderId) {

        log.debug("Get orderItems by order_id={} from database order_items", orderId);
        try {
            return jdbcTemplate.query(GET_BY_ORDER_ID, this::processRow, orderId);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }
}
