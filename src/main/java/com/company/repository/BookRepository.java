package com.company.repository;

import com.company.repository.entity.Book;

import java.util.List;

public interface BookRepository extends AbstractRepository<Long, Book> {
    Book getByIsbn(String isbn);

    List<Book> getByAuthor(String author);

    Book getByTitle(String title);

}
