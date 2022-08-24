package com.company.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderItem {
    private Long id;
    private Book book;
    private Order order;
    private Integer quantity;
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id) && Objects.equals(book, orderItem.book) && Objects.equals(order, orderItem.order) && Objects.equals(quantity, orderItem.quantity) && Objects.equals(price, orderItem.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, order, quantity, price);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", book=" + book +
                ", order=" + order.getId() +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
