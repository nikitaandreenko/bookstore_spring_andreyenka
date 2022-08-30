package com.company.service.impl;

import com.company.data.repository.BookRepository;
import com.company.entity.Book;
import com.company.service.BookService;
import com.company.service.dto.BookDtoService;
import com.company.service.dto.ObjectMapperService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service("bookService")
public class BookServiceImpl implements BookService {
    private static final Logger log = LogManager.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;
    private final ObjectMapperService mapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ObjectMapperService mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }


    @Override
    public Book create(BookDtoService book) {
        log.debug("Create book={} in database book", book);
        validateCreate(book);
        Book bookCreated = mapper.toEntity(book);
        return bookRepository.create(bookCreated);
    }

    @Override
    public BookDtoService findById(Long id) {
        log.debug("Get book by id={} from database books", id);
        Book book = bookRepository.findById(id);
        BookDtoService bookDtoService = mapper.toDto(book);
        if (bookDtoService == null) {
            throw new RuntimeException("Book with id:" + id + " doesn't exist");
        }
        return bookDtoService;
    }

    @Override
    public BookDtoService getByIsbn(String isbn) {
        log.debug("Get book by isbn={} from database books", isbn);
        Book book = bookRepository.getByIsbn(isbn);
        BookDtoService bookDtoService = mapper.toDto(book);
        if (bookDtoService == null) {
            throw new RuntimeException("Book with isbn:" + isbn + " doesn't exist");
        }
        return bookDtoService;
    }

    @Override
    public List<BookDtoService> findAll() {
        log.debug("Get all books from database books");
        return bookRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public List<BookDtoService> getByAuthor(String author) {
        log.debug("Get book by author={} from database books", author);
        return bookRepository.getByAuthor(author).stream().map(mapper::toDto).toList();
    }

    @Override
    public Long countAll() {
        log.debug("Count all books from database books");
        return bookRepository.countAll();
    }

    @Override
    public Book update(BookDtoService book) {
        log.debug("Update book={} in database books", book);
        validateUpdate(book);
        Book bookUpdated = mapper.toEntity(book);
        Book book1 = bookRepository.update(bookUpdated);
        return book1;
    }

    private void validateCreate(BookDtoService book) {
        if (book == null) {
            throw new RuntimeException("Books can't be empty...");
        }
        if (book.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Price is not valid. Price can't be less 0");
        }
        Book newBook = bookRepository.getByIsbn(book.getIsbn());
        if (newBook != null) {
            throw new RuntimeException("Book with isbn: " + book.getIsbn() + "already exist!");
        }
    }

    private void validateUpdate(BookDtoService book) {
        if (book.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Price is not valid. Price can't be less 0");
        }
        Book newBook = bookRepository.getByIsbn(book.getIsbn());
        if (newBook != null && !Objects.equals(book.getId(), newBook.getId())) {
            throw new RuntimeException("Book with isbn: " + book.getIsbn() + "already exist!");
        }
    }

    @Override
    public void delete(Long id) {
        log.debug("Delete book by id={} from database books", id);
        boolean successRemove = bookRepository.delete(id);
        if (!successRemove) {
            throw new RuntimeException("This book doesn't remove");
        }
    }

    @Override
    public BigDecimal totalPriceByAuthor(String author) {
        log.debug("Total books by author={} from database books", author);
        List<BookDtoService> books = getByAuthor(author);
        BigDecimal total = books.stream().map(BookDtoService::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        return total;
    }
}
