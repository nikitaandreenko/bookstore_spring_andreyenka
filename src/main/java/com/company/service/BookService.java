package com.company.service;

import com.company.entity.Book;
import com.company.service.dto.BookDtoService;

import java.math.BigDecimal;
import java.util.List;


public interface BookService extends AbstractService <Long, Book, BookDtoService> {
    BookDtoService getByIsbn(String isbn);

    List<BookDtoService> getByAuthor(String author);

    BigDecimal totalPriceByAuthor(String author);


}
