package com.company.service;

import com.company.repository.entity.User;
import com.company.service.dto.UserDto;

import java.util.List;

public interface UserService extends AbstractService<Long, UserDto>{

    UserDto getUserByEmail(String email);

    List<UserDto> getUserByLastName(String lastName);

    UserDto login(String email, String password);

    UserDto registration(UserDto user);

    UserDto updateRegistration(UserDto user);



}
