package com.company.service;

import com.company.entity.User;
import com.company.service.dto.UserDtoService;

import java.util.List;

public interface UserService extends AbstractService<Long, User, UserDtoService>{

    UserDtoService getUserByEmail(String email);

    List<UserDtoService> getUserByLastName(String lastName);

}
