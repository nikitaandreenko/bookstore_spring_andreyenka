package com.company.repository;

import com.company.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByLastName(String lastName);
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);

}
