package com.company.repository;

import com.company.repository.entity.User;
import com.company.service.dto.UserDto;

import java.util.List;

public interface UserRepository extends AbstractRepository<Long, User> {


    User getUserByEmail(String email);

    User login(String email, String password);

    List<User> getUserByLastName(String lastName);

    User registration(User user);

}
