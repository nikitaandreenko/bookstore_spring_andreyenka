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
        List<Book> books = entityManager.createQuery("from Book", Book.class).getResultList();
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
        entityManager.createQuery("update Book set availability = 'out of stock' where id = :id").setParameter("id", id).executeUpdate();
        return true;
    }

    @Override
    public Book getByIsbn(String isbn) {
        Book book = entityManager.find(Book.class, isbn);
        return book;
    }

    @Override
    public List<Book> getByAuthor(String author) {
        List<Book> books = entityManager.createQuery("FROM Book where author = :paramName", Book.class)
                .setParameter("paramName", author).getResultList();
        return books;
    }

}
