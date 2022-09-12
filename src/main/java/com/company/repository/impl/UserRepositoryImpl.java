package com.company.repository.impl;

;
import com.company.repository.UserRepository;
import com.company.repository.entity.Book;
import com.company.repository.entity.User;

import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository("userRepository")
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public User create(User entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public User findById(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = entityManager.createQuery("from User", User.class).getResultList();
        return users;
    }

    @Override
    public User update(User entity) {
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public boolean delete(Long id) {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            return false;
        }
        entityManager.createQuery("update User set lifeCycle = 'not active' where id = :id").setParameter("id", id).executeUpdate();
        return true;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = entityManager.find(User.class, email);
        return user;
    }

    @Override
    public User login(String email, String password) {
        User user = entityManager.createQuery("from User where email = :paramName1 and password = :paramName2", User.class)
                .setParameter("paramName1", email).setParameter("paramName2", password).getSingleResult();
        return user;
    }

    @Override
    public List<User> getUserByLastName(String lastName) {
        List<User> users = entityManager.createQuery("from User where lastName = :paramName", User.class)
                .setParameter("paramName", lastName).getResultList();
        return users;
    }
    @Override
    public User registration(User user) {
        entityManager.persist(user);
        return user;
    }
}
