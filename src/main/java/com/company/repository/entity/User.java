package com.company.repository.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
@SQLDelete(sql = "update users set life_cycle='not active' where id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_age")
    private int age;

    @Column(name = "email")
    private String email;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Column(name = "life_cycle")
    private String lifeCycle = "active";

    @Column(name = "user_password")
    private String password;

    public enum Role {
        USER, MANAGER, ADMIN
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && role == user.role && Objects.equals(lifeCycle, user.lifeCycle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, email, role, lifeCycle);
    }
}
