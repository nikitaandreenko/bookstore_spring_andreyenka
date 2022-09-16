package com.company.service.dto;

import com.company.repository.entity.Book;
import com.company.repository.entity.Order;
import com.company.repository.entity.OrderItem;
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
        dto.setAvailability(entity.getAvailability());
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
        entity.setAvailability(dto.getAvailability());
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
        dto.setPassword(entity.getPassword());
        dto.setLifeCycle(entity.getLifeCycle());
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
        entity.setLifeCycle(dto.getLifeCycle());
        entity.setPassword(dto.getPassword());
        return entity;
    }
    public User toEntityRegistration(UserDto dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAge(dto.getAge());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    public OrderDto toDto(Order entity) {
        OrderDto dto = new OrderDto();
        dto.setId(entity.getId());
        dto.setUser(toDto(entity.getUser()));
        dto.setStatus(OrderDto.Status.valueOf(entity.getStatus().toString()));
        dto.setTotalCost(entity.getTotalCost());
        dto.setItems(entity.getItems().stream().map(this::toDto).toList());
        return dto;
    }

    public Order toEntity(OrderDto dto){
        Order entity = new Order();
        entity.setId(dto.getId());
        entity.setUser(toEntity(dto.getUser()));
        entity.setStatus(Order.Status.valueOf(dto.getStatus().toString()));
        entity.setTotalCost(dto.getTotalCost());
        entity.setItems(dto.getItems().stream().map(this::toEntity).toList());
        return entity;
    }
    public OrderItemDto toDto(OrderItem entity) {
        OrderItemDto dto = new OrderItemDto();
        dto.setId(entity.getId());
        dto.setBook(toDto(entity.getBook()));
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        return dto;
    }
    public OrderItem toEntity(OrderItemDto dto){
        OrderItem entity = new OrderItem();
        entity.setId(dto.getId());
        entity.setPrice(dto.getPrice());
        entity.setBook(toEntity(dto.getBook()));
        entity.setQuantity(dto.getQuantity());
        return entity;
    }

}