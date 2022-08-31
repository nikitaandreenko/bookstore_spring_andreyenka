package com.company.repository.impl;

;
import com.company.repository.UserRepository;
import com.company.repository.entity.User;
import jakarta.persistence.EntityManager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("psql");
    private static final EntityManager entityManager = factory.createEntityManager();

    @Autowired
    public UserRepositoryImpl() {
    }

    @Override
    public User create(User entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public User findById(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public List<User> findAll() {
        List <User> users = entityManager.createQuery("from User", User.class).getResultList();
        return users;
    }

    @Override
    public User update(User entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(User.class, id));
        entityManager.getTransaction().commit();
    }

    @Override
    public User getUserByEmail(String email) {
        User user = entityManager.find(User.class, email);
        return user;
    }

    @Override
    public List<User> getUserByLastName(String lastName) {
        List <User> users = entityManager.createQuery("FROM User where lastName = :paramName", User.class)
                .setParameter("paramName", lastName).getResultList();
        return users;
    }

    public void close() {
        if (factory != null) {
        }
        factory.close();
    }

}
