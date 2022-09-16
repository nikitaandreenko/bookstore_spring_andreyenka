package com.company.repository.impl;

import com.company.repository.OrderRepository;
import com.company.repository.entity.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Repository("orderRepository")
@Transactional
public class OrderRepositoryImpl implements OrderRepository {
    public static final Class<Order> GET_BY_ID = Order.class;
    public static final String GET_ALL_ORDERS = "from Order";
    public static final String GET_BY_USER_ID = "SELECT o from Order o where o.user.id = ?1";
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order create(Order entity) {
        entityManager.persist(entity);
        entity.getItems().stream().peek(orderItem -> orderItem.setOrder(entity)).collect(Collectors.toList());
        return entity;
    }

    @Override
    public Order findById(Long id) {
        return entityManager.find(GET_BY_ID, id);
    }

    @Override
    public List<Order> findAll() {
        return entityManager.createQuery(GET_ALL_ORDERS, Order.class).getResultList();
    }

    @Override
    public Order update(Order entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<Order> findByUserId(Long userId) {

        return entityManager.createQuery(GET_BY_USER_ID)
                .setParameter(1, userId)
                .getResultList();
    }

}
