package com.company.data.dao;

import com.company.data.dto.UserDto;

import java.util.List;

public interface UserDao extends AbstractDao<Long, UserDto>{
    UserDto getUserByEmail(String email);

    List<UserDto> getUserByLastName(String lastName);
}
