package com.company.data.dto;

import com.company.entity.Book;
import com.company.entity.Order;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrderItemDto {
    private Long id;
    private Long bookId;
    private Long orderId;
    private Integer quantity;
    private BigDecimal price;

}
