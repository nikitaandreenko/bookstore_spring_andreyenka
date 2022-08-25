package com.company.repository.impl;

import com.company.entity.Order;
import com.company.entity.OrderItem;
import com.company.entity.User;
import com.company.repository.OrderDao;
import com.company.repository.OrderItemDao;
import com.company.repository.UserDao;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;
    private final OrderItemDao orderItemDao;

    @Autowired
    public OrderDaoImpl(JdbcTemplate jdbcTemplate, UserDao userDao, OrderItemDao orderItemDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
        this.orderItemDao = orderItemDao;
    }

    private static final Logger log = LogManager.getLogger(BookDaoImpl.class);

    public static final String GET_BY_ID = "SELECT o.id, o.user_id, (SELECT SUM (quantity * price) FROM order_items " +
            "WHERE order_id = o.id) AS total_cost, s.name AS status " +
            "FROM orders o JOIN statuses s  ON status_id = s.id WHERE s.name != 'CANCELED' AND o.id = ?";
    public static final String GET_ALL = "SELECT o.id, o.user_id, (SELECT SUM (quantity * price) FROM order_items " +
            "WHERE order_id = o.id) AS total_cost, s.name AS status " +
            "FROM orders o JOIN statuses s  ON o.status_id = s.id WHERE s.name != 'CANCELED'";

    private Order processRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        Long userId = rs.getLong("user_id");
        User user = userDao.findById(userId);
        order.setUser(user);
        order.setTotalCost(rs.getBigDecimal("total_cost"));
        order.setStatus(Order.Status.valueOf(rs.getString("status")));
        List<OrderItem> items = orderItemDao.findByOrderId(order.getId());
        order.setItems(items);
        return order;
    }

    @Override
    public Order create(Order entity) {
        return null;
    }

    @Override
    public Order findById(Long id) {
        log.debug("Get order by id={} from database orders", id);
        try {
            return jdbcTemplate.queryForObject(GET_BY_ID, this::processRow, id);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public List<Order> findAll() {
        log.debug("Get all orders from database orders");
        return jdbcTemplate.query(GET_ALL, this::processRow);
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
}
