package com.company.service;

import com.company.service.dto.BookDto;
import java.util.List;


public interface BookService extends AbstractService<Long, BookDto> {
    BookDto getByIsbn(String isbn);

    List<BookDto> getByAuthor(String author);

    BookDto getBookTitle(String title);

}
