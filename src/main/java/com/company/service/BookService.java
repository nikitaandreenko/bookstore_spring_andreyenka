package com.company.service;

import com.company.service.dto.BookDto;

import java.math.BigDecimal;
import java.util.List;


public interface BookService extends AbstractService<Long, BookDto> {
    BookDto getByIsbn(String isbn);

    List<BookDto> getByAuthor(String author);

    BigDecimal totalPriceByAuthor(String author);


}
