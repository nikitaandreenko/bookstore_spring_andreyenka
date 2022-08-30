package com.company.entity;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrderItem {
    private Long id;
    private Book book;
    private Order order;
    private Integer quantity;
    private BigDecimal price;

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
