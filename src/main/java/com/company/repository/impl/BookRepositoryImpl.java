package com.company.repository.impl;

import com.company.repository.BookRepository;
import com.company.repository.entity.Book;
import com.company.repository.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookRepository")
public class BookRepositoryImpl implements BookRepository {
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("psql");
    private static final EntityManager entityManager = factory.createEntityManager();

    @Autowired
    public BookRepositoryImpl() {
    }

    @Override
    public Book create(Book entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public Book findById(Long id) {
        Book book = entityManager.find(Book.class, id);
        return book;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = entityManager.createQuery("from Book ", Book.class).getResultList();
        return books;
    }


    @Override
    public Book update(Book entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(User.class, id));
        entityManager.getTransaction().commit();
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

    public void close() {
        if (factory != null) {
        }
        factory.close();
    }
}
