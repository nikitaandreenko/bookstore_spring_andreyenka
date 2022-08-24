package com.company.entity;

import lombok.*;

import java.util.Objects;

@Data
public class User {
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
