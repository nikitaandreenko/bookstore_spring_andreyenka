package com.company.service.dto;

import com.company.repository.entity.Book;
import com.company.repository.entity.Order;
import com.company.repository.entity.User;
import org.springframework.stereotype.Component;




@Component
public class ObjectMapperService {
    public BookDto toDto(Book entity) {
        BookDto dto = new BookDto();
        dto.setId(entity.getId());
        dto.setBookName(entity.getBookName());
        dto.setAuthor(entity.getAuthor());
        dto.setIsbn(entity.getIsbn());
        dto.setPrice(entity.getPrice());
        dto.setPages(entity.getPages());
        dto.setBinding(entity.getBinding());
        dto.setYearPublishing(entity.getYearPublishing());
        dto.setLanguage(BookDto.Language.valueOf(entity.getLanguage().toString()));
        return dto;
    }

    public Book toEntity(BookDto dto) {
        Book entity = new Book();
        entity.setId(dto.getId());
        entity.setBookName(dto.getBookName());
        entity.setAuthor(dto.getAuthor());
        entity.setIsbn(dto.getIsbn());
        entity.setPrice(dto.getPrice());
        entity.setPages(dto.getPages());
        entity.setBinding(dto.getBinding());
        entity.setYearPublishing(dto.getYearPublishing());
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
        dto.setUser(entity.getUser());
        dto.setStatus(OrderDto.Status.valueOf(entity.getStatus().toString()));
        dto.setTotalCost(entity.getTotalCost());
        dto.setItems(entity.getItems());
        return dto;
    }

    public Order toEntity(OrderDto dto) {
        Order entity = new Order();
        entity.setId(dto.getId());
        entity.setUser(dto.getUser());
        entity.setStatus(Order.Status.valueOf(dto.getStatus().toString()));
        entity.setTotalCost(dto.getTotalCost());
        entity.setItems(dto.getItems());
        return entity;
    }
}
