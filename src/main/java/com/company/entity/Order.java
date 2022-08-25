package com.company.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class Order {
    private Long id;
    private User user;
    private Status status;
    private BigDecimal totalCost;
    private List<OrderItem> items;

    public enum Status {
        PENDING, CONFIRMED, DELIVERED, CANCELED
    }

}
