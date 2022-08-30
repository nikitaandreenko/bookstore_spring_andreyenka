package com.company.service.dto;
import lombok.Data;

@Data
public class UserDtoService {
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
