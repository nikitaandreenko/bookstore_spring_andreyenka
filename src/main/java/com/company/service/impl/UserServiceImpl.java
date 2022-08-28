package com.company.service.impl;


import com.company.data.repository.UserRepository;
import com.company.entity.User;
import com.company.service.UserService;
import com.company.service.dto.ObjectMapperService;
import com.company.service.dto.UserDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service("userService")
public class UserServiceImpl implements UserService {
    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final ObjectMapperService mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ObjectMapperService mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


    @Override
    public User create(UserDtoService user) {
        log.debug("Create user={} in database user", user);
        validateCreate(user);
        User userCreated = mapper.toEntity(user);
        return userRepository.create(userCreated);
    }

    @Override
    public UserDtoService findById(Long id) {
        log.debug("Get user by id={} from database users", id);
        User user = userRepository.findById(id);
        UserDtoService userDtoService = mapper.toDto(user);
        if (userDtoService == null) {
            throw new RuntimeException("User with id:" + id + " doesn't exist");
        }
        return userDtoService;
    }

    @Override
    public UserDtoService getUserByEmail(String email) {
        log.debug("Get user by email={} from database users", email);
        User user = userRepository.getUserByEmail(email);
        UserDtoService userDtoService = mapper.toDto(user);
        if (userDtoService == null) {
            throw new RuntimeException("User with email:" + email + " doesn't exist");
        }
        return userDtoService;
    }

    @Override
    public List<UserDtoService> findAll() {
        log.debug("Get all users from database users");
        return userRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public List<UserDtoService> getUserByLastName(String lastName) {
        log.debug("Get user by LastName={} from database users", lastName);
        List<User> users = userRepository.getUserByLastName(lastName);
        if (users == null) {
            throw new RuntimeException("Users with lastname: " + lastName + " don't exist");
        }
        return users.stream().map(mapper::toDto).toList();
    }

    @Override
    public Long countAll() {
        log.debug("Count all users from database users");
        return userRepository.countAll();
    }

    @Override
    public User update(UserDtoService user) {
        log.debug("Update user={} in database users", user);
        validateUpdate(user);
        User userUpdated = mapper.toEntity(user);
        User user1 = userRepository.update(userUpdated);
        if (user1 == null) {
            throw new RuntimeException("Users can't be empty...");
        }
        return user1;
    }

    @Override
    public void delete(Long id) {
        log.debug("Delete user by id={} in database users", id);
        boolean successRemove = userRepository.delete(id);
        if (!successRemove) {
            throw new RuntimeException("This user doesn't remove");
        }
    }

    private void validateCreate(UserDtoService user) {
        User newUser = userRepository.getUserByEmail(user.getEmail());
        if (newUser != null) {
            throw new RuntimeException("User with email: " + user.getEmail() + "already exist!");
        }
    }

    private void validateUpdate(UserDtoService user) {
        User newUser = userRepository.getUserByEmail(user.getEmail());
        if (!Objects.equals(user.getId(), newUser.getId())) {
            throw new RuntimeException("User with email: " + user.getEmail() + "already exist!");
        }
    }
}
