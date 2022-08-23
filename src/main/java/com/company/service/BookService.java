package com.company.service;

import com.company.entity.Book;

import java.math.BigDecimal;
import java.util.List;


public interface BookService extends AbstractService <Long, Book> {
    Book getByIsbn(String isbn);

    List<Book> getByAuthor(String author);

    BigDecimal totalPriceByAuthor(String author);


}
