package com.company.data.repository.impl;

import com.company.data.dao.UserDao;
import com.company.data.dto.ObjectMapper;
import com.company.data.dto.UserDto;
import com.company.data.repository.UserRepository;
import com.company.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

    public final UserDao userDao;
    private final ObjectMapper mapper;

    @Autowired
    public UserRepositoryImpl(UserDao userDao, ObjectMapper mapper) {
        this.userDao = userDao;
        this.mapper = mapper;
    }


    @Override
    public User create(User entity) {
        UserDto dto = mapper.toDto(entity);
        UserDto createDto = userDao.create(dto);
        return createDto != null ? mapper.toEntity(createDto) : null;
    }

    @Override
    public User findById(Long id) {
        UserDto dto = userDao.findById(id);
        User entity = null;
        if (dto != null) {
            entity = mapper.toEntity(dto);
        }
        return entity;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll().stream().map(mapper::toEntity).toList();
    }

    @Override
    public Long countAll() {
        return userDao.countAll();
    }

    @Override
    public User update(User entity) {
        UserDto dto = mapper.toDto(entity);
        UserDto updateDto = userDao.update(dto);
        return updateDto != null ? mapper.toEntity(updateDto) : null;
    }

    @Override
    public boolean delete(Long id) {
        return userDao.delete(id);
    }

    @Override
    public User getUserByEmail(String email) {
        UserDto dto = userDao.getUserByEmail(email);
        User entity = null;
        if (dto != null) {
            entity = mapper.toEntity(dto);
        }
        return entity;
    }

    @Override
    public List<User> getUserByLastName(String lastName) {
        return userDao.getUserByLastName(lastName).stream().map(mapper::toEntity).toList();
    }
}
