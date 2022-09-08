package com.company.service.dto;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private Role role;
    private String lifeCycle;

    public enum Role {
        USER, MANAGER, ADMIN
    }

}
