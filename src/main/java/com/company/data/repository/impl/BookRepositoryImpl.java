package com.company.data.repository.impl;

import com.company.data.dao.BookDao;
import com.company.data.dto.BookDto;
import com.company.data.dto.ObjectMapper;
import com.company.data.repository.BookRepository;
import com.company.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookRepository")
public class BookRepositoryImpl implements BookRepository {
    private final BookDao bookDao;
    private final ObjectMapper mapper;

    @Autowired
    public BookRepositoryImpl(BookDao bookDao, ObjectMapper mapper) {
        this.bookDao = bookDao;
        this.mapper = mapper;
    }


    @Override
    public Book create(Book entity) {
        BookDto dto = mapper.toDto(entity);
        BookDto createDto = bookDao.create(dto);
        return createDto != null ? mapper.toEntity(createDto) : null;
    }

    @Override
    public Book findById(Long id) {
        BookDto dto = bookDao.findById(id);
        Book entity = null;
        if (dto != null) {
            entity = mapper.toEntity(dto);
        }
        return entity;
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll().stream().map(mapper::toEntity).toList();
    }

    @Override
    public Long countAll() {
        return bookDao.countAll();
    }

    @Override
    public Book update(Book entity) {
        BookDto dto = mapper.toDto(entity);
        BookDto updateDto = bookDao.update(dto);
        return updateDto != null ? mapper.toEntity(updateDto) : null;
    }

    @Override
    public boolean delete(Long id) {
        return bookDao.delete(id);
    }

    @Override
    public Book getByIsbn(String isbn) {
        BookDto dto = bookDao.getByIsbn(isbn);
        Book entity = null;
        if (dto != null) {
            entity = mapper.toEntity(dto);
        }
        return entity;
    }

    @Override
    public List<Book> getByAuthor(String author) {
        return bookDao.getByAuthor(author).stream().map(mapper::toEntity).toList();
    }
}
