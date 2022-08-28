package com.company.service.dto;

import com.company.entity.Book;
import com.company.entity.Order;
import com.company.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperService {
    public BookDtoService toDto(Book entity) {
        BookDtoService dto = new BookDtoService();
        dto.setId(entity.getId());
        dto.setBook_name(entity.getBook_name());
        dto.setAuthor(entity.getAuthor());
        dto.setIsbn(entity.getIsbn());
        dto.setPrice(entity.getPrice());
        dto.setPages(entity.getPages());
        dto.setBinding(entity.getBinding());
        dto.setYear_publishing(entity.getYear_publishing());
        dto.setLanguage(BookDtoService.Language.valueOf(entity.getLanguage().toString()));
        return dto;
    }

    public Book toEntity(BookDtoService dto) {
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

    public UserDtoService toDto(User entity) {
        UserDtoService dto = new UserDtoService();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setAge(entity.getAge());
        dto.setEmail(entity.getEmail());
        dto.setRole(UserDtoService.Role.valueOf(entity.getRole().toString()));
        return dto;
    }

    public User toEntity(UserDtoService dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAge(dto.getAge());
        entity.setEmail(dto.getEmail());
        entity.setRole(User.Role.valueOf(dto.getRole().toString()));
        return entity;
    }

    public OrderDtoService toDto(Order entity) {
        OrderDtoService dto = new OrderDtoService();
        dto.setId(entity.getId());
        dto.setUser(entity.getUser());
        dto.setStatus(OrderDtoService.Status.valueOf(entity.getStatus().toString()));
        dto.setTotalCost(entity.getTotalCost());
        dto.setItems(entity.getItems());
        return dto;
    }

    public Order toEntity(OrderDtoService dto) {
        Order entity = new Order();
        entity.setId(dto.getId());
        entity.setUser(dto.getUser());
        entity.setStatus(Order.Status.valueOf(entity.getStatus().toString()));
        entity.setStatus(Order.Status.valueOf(dto.getStatus().toString()));
        entity.setTotalCost(dto.getTotalCost());
        entity.setItems(dto.getItems());
        return entity;
    }
}
