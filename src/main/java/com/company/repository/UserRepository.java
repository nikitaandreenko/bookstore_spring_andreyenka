package com.company.repository;

import com.company.repository.entity.User;

import java.util.List;

public interface UserRepository extends AbstractRepository<Long, User> {


    User getUserByEmail(String email);

    List<User> getUserByLastName(String lastName);

}
