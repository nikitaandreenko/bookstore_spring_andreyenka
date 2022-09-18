package com.company.repository.impl;

import com.company.repository.OrderRepository;
import com.company.repository.entity.Book;
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
    public static final String SOFT_DELETE_ORDER = "update Order set status = 'CANSELED' where id = :id";
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
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public boolean delete(Long id) {
        Order order = entityManager.find(Order.class, id);
        if (order == null) {
            return false;
        }
        entityManager.createQuery(SOFT_DELETE_ORDER).setParameter("id", id).executeUpdate();
        return true;
    }

    @Override
    public List<Order> findByUserId(Long userId) {

        return entityManager.createQuery(GET_BY_USER_ID)
                .setParameter(1, userId)
                .getResultList();
    }

}
