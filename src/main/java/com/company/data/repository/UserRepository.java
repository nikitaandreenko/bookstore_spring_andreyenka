package com.company.data.repository;

import com.company.entity.User;

import java.util.List;

public interface UserRepository extends AbstractRepository<Long, User> {


    User getUserByEmail(String email);

    List<User> getUserByLastName(String lastName);

}
