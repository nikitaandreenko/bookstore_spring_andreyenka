package com.company.repository.impl;

import com.company.repository.OrderRepository;

import com.company.repository.entity.Order;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository("orderRepository")
public class OrderRepositoryImpl implements OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

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
        List<Order> orders = entityManager.createQuery("from Order", Order.class).getResultList();
        return orders;
    }

    @Override
    public Order update(Order entity) {
        return null;
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public List<Order> findByUserId(Long userId) {

        List<Order> orders = entityManager.createQuery("SELECT o from Order o where o.user.id = ?1")
                .setParameter(1, userId)
                .getResultList();
        return orders;
    }

}
