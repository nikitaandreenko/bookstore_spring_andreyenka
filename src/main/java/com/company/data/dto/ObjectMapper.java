package com.company.data.dto;

import com.company.entity.Book;
import com.company.entity.Order;
import com.company.entity.OrderItem;
import com.company.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapper {

    public BookDto toDto(Book entity) {
        BookDto dto = new BookDto();
        dto.setId(entity.getId());
        dto.setBook_name(entity.getBook_name());
        dto.setAuthor(entity.getAuthor());
        dto.setIsbn(entity.getIsbn());
        dto.setPrice(entity.getPrice());
        dto.setPages(entity.getPages());
        dto.setBinding(entity.getBinding());
        dto.setYear_publishing(entity.getYear_publishing());
        dto.setLanguage(BookDto.Language.valueOf(entity.getLanguage().toString()));
        return dto;
    }

    public Book toEntity(BookDto dto) {
        Book entity = new Book();
        entity.setId(dto.getId());
        entity.setBook_name(dto.getBook_name());
        entity.setAuthor(dto.getAuthor());
        entity.setIsbn(dto.getIsbn());
        entity.setPrice(dto.getPrice());
        entity.setPages(dto.getPages());
        entity.setBinding(dto.getBinding());
        entity.setYear_publishing(dto.getYear_publishing());
        entity.setLanguage(Book.Language.valueOf(dto.getLanguage().toString()));
        return entity;
    }

    public UserDto toDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setAge(entity.getAge());
        dto.setEmail(entity.getEmail());
        dto.setRole(UserDto.Role.valueOf(entity.getRole().toString()));
        return dto;
    }

    public User toEntity(UserDto dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAge(dto.getAge());
        entity.setEmail(dto.getEmail());
        entity.setRole(User.Role.valueOf(dto.getRole().toString()));
        return entity;
    }

    public OrderDto toDto(Order entity) {
        OrderDto dto = new OrderDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setStatus(OrderDto.Status.valueOf(entity.getStatus().toString()));
        dto.setTotalCost(entity.getTotalCost());
        return dto;
    }

    public Order toEntity(OrderDto dto) {
        Order entity = new Order();
        entity.setId(dto.getId());
        entity.setStatus(Order.Status.valueOf(dto.getStatus().toString()));
        entity.setTotalCost(dto.getTotalCost());
        return entity;
    }

    public OrderItemDto toDto(OrderItem entity) {
        OrderItemDto dto = new OrderItemDto();
        dto.setId(entity.getId());
        dto.setBookId(entity.getBook().getId());
        dto.setOrderId(entity.getOrder().getId());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    public OrderItem toEntity(OrderItemDto dto) {
        OrderItem entity = new OrderItem();
        entity.setId(dto.getId());
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());
        return entity;
    }


}
