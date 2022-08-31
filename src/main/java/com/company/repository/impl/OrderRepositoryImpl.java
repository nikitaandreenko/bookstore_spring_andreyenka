package com.company.repository.impl;

import com.company.repository.OrderRepository;

import com.company.repository.entity.Order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("orderRepository")
public class OrderRepositoryImpl implements OrderRepository {
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("psql");
    private static final EntityManager entityManager = factory.createEntityManager();

    public OrderRepositoryImpl() {
    }

    @Override
    public Order create(Order entity) {
        return null;
    }


    @Override
    public Order findById(Long id) {
        Order order = entityManager.find(Order.class, id);
        return order;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = entityManager.createQuery("from Order ", Order.class).getResultList();
        return orders;
    }

    @Override
    public Order update(Order entity) {
        return null;
    }

    @Override
    public void delete(Long id) {
        System.out.println(id);
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        List<Order> orders = entityManager.createQuery("FROM Order where user = :paramName", Order.class)
                .setParameter("paramName", userId).getResultList();
        return orders;
    }
    public void close() {
        if (factory != null) {
        }
        factory.close();
    }
}
