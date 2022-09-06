package com.company.repository.impl;

;
import com.company.repository.UserRepository;
import com.company.repository.entity.User;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;


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
        List<User> users = entityManager.createQuery("from User where isDeleted = false", User.class).getResultList();
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
        entityManager.createQuery("update User set isDeleted = true where id = :id").setParameter("id", id).executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public User getUserByEmail(String email) {
        User user = entityManager.find(User.class, email);
        return user;
    }

    @Override
    public List<User> getUserByLastName(String lastName) {
        List<User> users = entityManager.createQuery("FROM User where lastName = :paramName", User.class)
                .setParameter("paramName", lastName).getResultList();
        return users;
    }

}
