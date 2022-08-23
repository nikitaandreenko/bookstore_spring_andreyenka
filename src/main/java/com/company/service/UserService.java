package com.company.service;

import com.company.entity.User;

import java.util.List;

public interface UserService extends AbstractService<Long, User>{

    User getUserByEmail(String email);

    List<User> getUserByLastName(String lastName);

}
