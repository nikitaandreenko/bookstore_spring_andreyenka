package com.company.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
@SQLDelete(sql = "update orders set status='CANCELED' where id=?")
public class Order {
    public Order() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;


    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;

    public enum Status {
        PENDING, CONFIRMED, DELIVERED, CANCELED
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(user, order.user) && status == order.status && Objects.equals(totalCost, order.totalCost) && Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, status, totalCost, items);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user.getId() +
                ", status=" + status +
                ", totalCost=" + totalCost +
                ", items=" + items +
                '}';
    }
}
