package com.company.data.repository;

import com.company.entity.Book;

import java.util.List;

public interface BookRepository extends AbstractRepository<Long, Book> {
    Book getByIsbn(String isbn);

    List<Book> getByAuthor(String author);
}
