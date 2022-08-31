package com.company.service.dto;

import com.company.repository.entity.OrderItem;
import com.company.repository.entity.User;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class OrderDto {
    private Long id;
    private User user;
    private Status status;
    private BigDecimal totalCost;
    private List<OrderItem> items;

    public enum Status {
        PENDING, CONFIRMED, DELIVERED, CANCELED
    }

}
