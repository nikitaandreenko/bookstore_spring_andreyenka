package com.company.service.dto;

public class CartDto {

    private BookDto bookDto;
    private int quantity;

    public CartDto(BookDto bookDto, int quantity) {
        this.bookDto = bookDto;
        this.quantity = quantity;
    }

    public BookDto getBookDto() {
        return bookDto;
    }

    public void setBookDto(BookDto bookDto) {
        this.bookDto = bookDto;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
