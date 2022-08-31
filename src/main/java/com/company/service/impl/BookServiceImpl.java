package com.company.service.impl;

import com.company.repository.BookRepository;
import com.company.repository.entity.Book;
import com.company.service.BookService;
import com.company.service.dto.BookDto;
import com.company.service.dto.ObjectMapperService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


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
    public BookDto create(BookDto book) {
        log.debug("Create book={} in database book", book);
        validateCreate(book);
        Book bookCreated = mapper.toEntity(book);
        bookRepository.create(bookCreated);
        return mapper.toDto(bookCreated);
    }

    @Override
    public BookDto findById(Long id) {
        log.debug("Get book by id={} from database books", id);
        Book book = bookRepository.findById(id);
        BookDto bookDto = mapper.toDto(book);
        if (bookDto == null) {
            throw new RuntimeException("Book with id:" + id + " doesn't exist");
        }
        return bookDto;
    }

    @Override
    public BookDto getByIsbn(String isbn) {
        log.debug("Get book by isbn={} from database books", isbn);
        Book book = bookRepository.getByIsbn(isbn);
        BookDto bookDto = mapper.toDto(book);
        if (bookDto == null) {
            throw new RuntimeException("Book with isbn:" + isbn + " doesn't exist");
        }
        return bookDto;
    }

    @Override
    public List<BookDto> findAll() {
        log.debug("Get all books from database books");
        List<Book> books = bookRepository.findAll();
        return books.stream().map(mapper::toDto).toList();
    }

    @Override
    public List<BookDto> getByAuthor(String author) {
        log.debug("Get book by author={} from database books", author);
        List<Book> books = bookRepository.getByAuthor(author);
        return books.stream().map(mapper::toDto).toList();
    }

    @Override
    public BookDto update(BookDto book) {
        log.debug("Update book={} in database books", book);
        validateUpdate(book);
        Book bookUpdated = mapper.toEntity(book);
        bookRepository.update(bookUpdated);
        return mapper.toDto(bookUpdated);
    }

    private void validateCreate(BookDto book) {
        if (book == null) {
            throw new RuntimeException("Books can't be empty...");
        }
        if (book.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Price is not valid. Price can't be less 0");
        }

    }

    private void validateUpdate(BookDto book) {
        if (book.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Price is not valid. Price can't be less 0");
        }
    }

    @Override
    public void delete(Long id) {
        log.debug("Delete book by id={} from database books", id);
        bookRepository.delete(id);
    }

    @Override
    public BigDecimal totalPriceByAuthor(String author) {
        log.debug("Total books by author={} from database books", author);
        List<BookDto> books = getByAuthor(author);
        BigDecimal total = books.stream().map(BookDto::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        return total;
    }
}
