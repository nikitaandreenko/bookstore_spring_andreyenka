package com.company.repository.impl;

import com.company.repository.UserDao;
import com.company.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

    public static final String GET_ALL = "SELECT users.id, users.first_name, users.last_name, users.age, users.email, roles.name " +
            "FROM users JOIN roles ON role_id = roles.id";
    public static final String GET_BY_ID = "SELECT users.id, users.first_name, users.last_name, users.age, users.email, roles.name " +
            "FROM users JOIN roles ON role_id = roles.id WHERE users.id = ?";

    public static final String CREATE_USER = "INSERT INTO users (first_name, last_name, age, email, role_id) " +
            "VALUES (?, ?, ?, ?, (SELECT id FROM roles WHERE name = ?))";

    public static final String GET_BY_EMAIL = "SELECT users.id, users.first_name, users.last_name, users.age, users.email, roles.name " +
            "FROM users JOIN roles ON role_id = roles.id WHERE users.email = ?";
    public static final String UPDATE_NAMED = "UPDATE users SET first_name = :first_name, last_name = :last_name, age = :age, email = :email, " +
            "role_id = (SELECT id FROM roles WHERE name = :name) WHERE id = :id";
    public static final String GET_ALL_LASTNAME = "SELECT users.id, users.first_name, users.last_name, users.age, users.email, roles.name " +
            "FROM users JOIN roles ON role_id = roles.id WHERE users.last_name= ?";
    public static final String DELETE_BY_ID = "UPDATE users SET deleted = TRUE WHERE id = ?";
    public static final String COUNT_All_USERS = "SELECT count(*) AS total FROM users";

    private final JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    private User processRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setAge(rs.getInt("age"));
        user.setEmail(rs.getString("email"));
        user.setRole(User.Role.valueOf(rs.getString("name")));
        return user;
    }

    @Override
    public User create(User user) {
        log.debug("Create user={} in database user", user);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setLong(3, user.getAge());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getRole().toString());
            return ps;
        }, keyHolder);
        Long id = (Long) keyHolder.getKeys().get("id");
        if (id != null) {
            return findById(id);
        }
        return null;
    }

    @Override
    public User findById(Long id) {
        log.debug("Get user by id={} from database users", id);
        try {
            return jdbcTemplate.queryForObject(GET_BY_ID, this::processRow, id);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        log.debug("Get book by email={} from database users", email);
        try {
            return jdbcTemplate.queryForObject(GET_BY_EMAIL, this::processRow, email);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        log.debug("Get all users from database users");
        return jdbcTemplate.query(GET_ALL, this::processRow);
    }

    @Override
    public List<User> getUserByLastName(String lastName) {
        log.debug("Get book by lastName={} from database users", lastName);
        return jdbcTemplate.query(GET_ALL_LASTNAME, this::processRow, lastName);
    }

    @Override
    public Long countAll() {
        log.debug("Count all users from database users");
        return jdbcTemplate.query(COUNT_All_USERS, (ResultSet rs) -> {
            Long total = rs.getLong("total");
            return total;
        });
    }

    @Override
    public User update(User user) {
        log.debug("Update user={} in database users", user);
        Map<String, Object> map = new HashMap<>();
        map.put("first_name", user.getFirstName());
        map.put("last_name", user.getLastName());
        map.put("age", user.getAge());
        map.put("email", user.getEmail());
        map.put("name", user.getRole().toString());
        map.put("id", user.getId());
        namedJdbcTemplate.update(UPDATE_NAMED, map);
        return findById(user.getId());
    }

    @Override
    public boolean delete(Long id) {
        log.debug("Delete user by id={} from database users", id);
        int rowsUpdated = jdbcTemplate.update(DELETE_BY_ID, id);
        return rowsUpdated == 1;
    }
}
