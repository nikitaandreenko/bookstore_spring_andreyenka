package com.company.service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class OrderDto {
    private Long id;
    private UserDto user;
    private Status status;
    private BigDecimal totalCost;
    private List<OrderItemDto> items;

    public enum Status {
        PENDING, CONFIRMED, DELIVERED, CANCELED
    }

}
