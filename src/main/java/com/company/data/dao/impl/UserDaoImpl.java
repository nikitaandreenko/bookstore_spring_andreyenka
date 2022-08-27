package com.company.data.dao.impl;

import com.company.data.dao.UserDao;
import com.company.data.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

    public static final String GET_ALL = "SELECT users.id, users.first_name, users.last_name, users.age, users.email, roles.name " +
            "FROM users JOIN roles ON role_id = roles.id WHERE users.deleted = FALSE";
    public static final String GET_BY_ID = "SELECT users.id, users.first_name, users.last_name, users.age, users.email, roles.name " +
            "FROM users JOIN roles ON role_id = roles.id WHERE users.deleted = FALSE AND users.id = ?";

    public static final String CREATE_USER = "INSERT INTO users (first_name, last_name, age, email, role_id) " +
            "VALUES (?, ?, ?, ?, (SELECT id FROM roles WHERE name = ?)) ";

    public static final String GET_BY_EMAIL = "SELECT users.id, users.first_name, users.last_name, users.age, users.email, roles.name " +
            "FROM users JOIN roles ON role_id = roles.id WHERE users.deleted = FALSE AND users.email = ?";
    public static final String UPDATE_NAMED = "UPDATE users SET first_name = :first_name, last_name = :last_name, age = :age, email = :email, " +
            "role_id = (SELECT id FROM roles WHERE name = :name) WHERE deleted = FALSE AND id = :id";
    public static final String GET_ALL_LASTNAME = "SELECT users.id, users.first_name, users.last_name, users.age, users.email, roles.name " +
            "FROM users JOIN roles ON role_id = roles.id WHERE user.deleted = FALSE AND users.last_name= ?";
    public static final String DELETE_BY_ID = "UPDATE users SET deleted = TRUE WHERE id = ?";
    public static final String COUNT_All_USERS = "SELECT count(*) AS total FROM users WHERE user.deleted = FALSE ";

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

    private UserDto processRow(ResultSet rs, int rowNum) throws SQLException {
        UserDto userDto = new UserDto();
        userDto.setId(rs.getLong("id"));
        userDto.setFirstName(rs.getString("first_name"));
        userDto.setLastName(rs.getString("last_name"));
        userDto.setAge(rs.getInt("age"));
        userDto.setEmail(rs.getString("email"));
        userDto.setRole(UserDto.Role.valueOf(rs.getString("name")));
        return userDto;
    }

    @Override
    public UserDto create(UserDto userDto) {
        log.debug("Create user={} in database user", userDto);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userDto.getFirstName());
            ps.setString(2, userDto.getLastName());
            ps.setLong(3, userDto.getAge());
            ps.setString(4, userDto.getEmail());
            ps.setString(5, userDto.getRole().toString());
            return ps;
        }, keyHolder);
        Long id = (Long) keyHolder.getKeys().get("id");
        if (id != null) {
            return findById(id);
        }
        return null;
    }

    @Override
    public UserDto findById(Long id) {
        log.debug("Get user by id={} from database users", id);
        try {
            return jdbcTemplate.queryForObject(GET_BY_ID, this::processRow, id);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public List<UserDto> findAll() {
        log.debug("Get all users from database users");
        return jdbcTemplate.query(GET_ALL, this::processRow);
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
    public UserDto update(UserDto userDto) {
        log.debug("Update user={} in database users", userDto);
        Map<String, Object> map = new HashMap<>();
        map.put("first_name", userDto.getFirstName());
        map.put("last_name", userDto.getLastName());
        map.put("age", userDto.getAge());
        map.put("email", userDto.getEmail());
        map.put("name", userDto.getRole().toString());
        map.put("id", userDto.getId());
        namedJdbcTemplate.update(UPDATE_NAMED, map);
        return findById(userDto.getId());
    }

    @Override
    public boolean delete(Long id) {
        log.debug("Delete user by id={} from database users", id);
        int rowsUpdated = jdbcTemplate.update(DELETE_BY_ID, id);
        return rowsUpdated == 1;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        log.debug("Get book by email={} from database users", email);
        try {
            return jdbcTemplate.queryForObject(GET_BY_EMAIL, this::processRow, email);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public List<UserDto> getUserByLastName(String lastName) {
        log.debug("Get book by lastName={} from database users", lastName);
        return jdbcTemplate.query(GET_ALL_LASTNAME, this::processRow, lastName);
    }
}
