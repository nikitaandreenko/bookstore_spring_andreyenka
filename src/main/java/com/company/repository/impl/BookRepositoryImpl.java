package com.company.repository.impl;

import com.company.repository.BookRepository;
import com.company.repository.entity.Book;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository("bookRepository")
@Transactional
public class BookRepositoryImpl implements BookRepository {

    public static final String ALL_BOOKS = "from Book";
    public static final String SOFT_DELETE_BOOK = "update Book set availability = 'out of stock' where id = :id";
    public static final Class<Book> GET_BY_ISBN = Book.class;
    public static final String GET_BY_AUTHOR = "FROM Book where author = :paramName";
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book create(Book entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Book findById(Long id) {
        Book book = entityManager.find(Book.class, id);
        return book;
    }
    @Override
    public List<Book> findAll() {
        List<Book> books = entityManager.createQuery(ALL_BOOKS, Book.class).getResultList();
        return books;
    }
    @Override
    public Book update(Book entity) {
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public boolean delete(Long id) {
        Book book = entityManager.find(Book.class, id);
        if (book == null) {
            return false;
        }
        entityManager.createQuery(SOFT_DELETE_BOOK).setParameter("id", id).executeUpdate();
        return true;
    }

    @Override
    public Book getByIsbn(String isbn) {
        return entityManager.find(GET_BY_ISBN, isbn);
    }

    @Override
    public List<Book> getByAuthor(String author) {
        return entityManager.createQuery(GET_BY_AUTHOR, Book.class)
                .setParameter("paramName", author).getResultList();
    }

}
