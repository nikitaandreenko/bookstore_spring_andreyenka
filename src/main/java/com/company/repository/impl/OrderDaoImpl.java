package com.company.repository.impl;

import com.company.entity.Order;
import com.company.entity.OrderItem;
import com.company.entity.User;
import com.company.repository.OrderDao;
import com.company.repository.OrderItemDao;
import com.company.repository.UserDao;
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
public class OrderDaoImpl implements OrderDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;
    private final OrderItemDao orderItemDao;

    private static final Logger log = LogManager.getLogger(BookDaoImpl.class);

    public static final String GET_BY_ID = "SELECT o.id, o.user_id, o.total_cost, s.name " +
            "FROM orders o JOIN status s  ON status_id = s.id WHERE o.id = ?";
    public static final String GET_ALL = "SELECT o.id, o.user_id, o.total_cost, s.name " +
            "FROM orders o JOIN status s  ON status_id = s.id";

    private Order processRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        Long userId = rs.getLong("user_id");
        User user = userDao.findById(userId);
        order.setUser(user);
        order.setStatus(Order.Status.valueOf(rs.getString("status")));
        order.setTotalCost(rs.getBigDecimal("total_cost"));
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
