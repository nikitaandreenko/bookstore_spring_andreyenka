package com.company.service.impl;


import com.company.data.repository.UserRepository;
import com.company.entity.User;
import com.company.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User create(User user) {
        log.debug("Create user={} in database user", user);
        return userRepository.create(user);
    }

    @Override
    public User findById(Long id) {
        log.debug("Get user by id={} from database users", id);
        User user = userRepository.findById(id);
        if (user == null) {
            throw new RuntimeException("User with id:" + id + " doesn't exist");
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        log.debug("Get user by email={} from database users", email);
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            throw new RuntimeException("User with email:" + email + " doesn't exist");
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        log.debug("Get all users from database users");
        return userRepository.findAll();
    }

    @Override
    public List<User> getUserByLastName(String lastName) {
        log.debug("Get user by LastName={} from database users", lastName);
        List<User> users = userRepository.getUserByLastName(lastName);
        if (users == null) {
            throw new RuntimeException("Users with lastname: " + lastName + " don't exist");
        }
        return users;
    }

    @Override
    public Long countAll() {
        log.debug("Count all users from database users");
        return userRepository.countAll();
    }

    @Override
    public User update(User user) {
        log.debug("Update user={} in database users", user);
        User user1 = userRepository.update(user);
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
}
