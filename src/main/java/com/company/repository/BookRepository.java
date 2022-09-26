package com.company.repository;

import com.company.repository.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository <Book, Long> {
    Book findByIsbn(String isbn);

    List<Book> findByAuthor(String author);

    Book findByBookName(String title);

}
