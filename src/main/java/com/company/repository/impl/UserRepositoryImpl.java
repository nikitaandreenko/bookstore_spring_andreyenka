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

    public static final Class<User> GET_BY_ID = User.class;
    public static final String GET_ALL_USER = "from User";
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public User create(User entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(GET_BY_ID, id);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery(GET_ALL_USER, User.class).getResultList();
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
        return entityManager.find(User.class, email);
    }

    @Override
    public User login(String email, String password) {
        User user = entityManager.createQuery("from User where email = :paramName1 and password = :paramName2", User.class)
                .setParameter("paramName1", email).setParameter("paramName2", password).getSingleResult();
        return user;
    }

    @Override
    public List<User> getUserByLastName(String lastName) {
        return entityManager.createQuery("from User where lastName = :paramName", User.class)
                .setParameter("paramName", lastName).getResultList();
    }
    @Override
    public User registration(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User updateRegistration(User user) {
        entityManager.persist(user);
        return user;
    }
}
