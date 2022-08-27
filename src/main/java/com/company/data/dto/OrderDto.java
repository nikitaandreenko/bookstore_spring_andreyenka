package com.company.data.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private Status status;
    private BigDecimal totalCost;

    public enum Status {
        PENDING, CONFIRMED, DELIVERED, CANCELED
    }
}
