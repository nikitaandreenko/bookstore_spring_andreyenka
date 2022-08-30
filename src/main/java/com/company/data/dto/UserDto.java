package com.company.data.dto;

import com.company.entity.User;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private Role role;

    public enum Role {
        USER, MANAGER, ADMIN
    }

}
