package com.company.data.dao.impl;

import com.company.data.dao.OrderDao;
import com.company.data.dto.OrderDto;

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
public class OrderDaoImpl implements OrderDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final Logger log = LogManager.getLogger(OrderDaoImpl.class);

    public static final String GET_BY_ID = "SELECT o.id, o.user_id, (SELECT SUM (quantity * price) FROM order_items " +
            "WHERE order_id = o.id) AS total_cost, s.name AS status " +
            "FROM orders o JOIN statuses s  ON status_id = s.id WHERE s.name != 'CANCELED' AND o.id = ?";
    public static final String GET_ALL = "SELECT o.id, o.user_id, (SELECT SUM (quantity * price) FROM order_items " +
            "WHERE order_id = o.id) AS total_cost, s.name AS status " +
            "FROM orders o JOIN statuses s  ON o.status_id = s.id WHERE s.name != 'CANCELED'";
    public static final String GET_BY_USER_ID = "SELECT o.id, o.user_id, (SELECT SUM (quantity * price) FROM order_items " +
            "WHERE order_id = o.id) AS total_cost, s.name AS status " +
            "FROM orders o JOIN statuses s  ON status_id = s.id WHERE s.name != 'CANCELED' AND user_id = ?";


    private OrderDto processRow(ResultSet rs, int rowNum) throws SQLException {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(rs.getLong("id"));
        orderDto.setUserId(rs.getLong("user_id"));
        orderDto.setStatus(OrderDto.Status.valueOf(rs.getString("status")));
        orderDto.setTotalCost(rs.getBigDecimal("total_cost"));
        return orderDto;
    }

    @Override
    public OrderDto create(OrderDto entity) {

        return null;
    }

    @Override
    public OrderDto findById(Long id) {
        log.debug("Get order by id={} from database orders", id);
        try {
            return jdbcTemplate.queryForObject(GET_BY_ID, this::processRow, id);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public List<OrderDto> findAll() {
        log.debug("Get all orders from database orders");
        return jdbcTemplate.query(GET_ALL, this::processRow);
    }

    @Override
    public Long countAll() {
        return null;
    }

    @Override
    public OrderDto update(OrderDto entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<OrderDto> findByUserId(Long userId) {
        log.debug("Get order by user_id={} from database order_items", userId);
        try {
            return jdbcTemplate.query(GET_BY_USER_ID, this::processRow, userId);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }
}
