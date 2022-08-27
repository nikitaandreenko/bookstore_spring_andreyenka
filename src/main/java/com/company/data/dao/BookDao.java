package com.company.data.dao;

import com.company.data.dto.BookDto;

import java.util.List;

public interface BookDao extends AbstractDao<Long, BookDto> {
    BookDto getByIsbn(String isbn);

    List<BookDto> getByAuthor(String author);
}
