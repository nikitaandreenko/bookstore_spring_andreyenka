package com.company.service.impl;

import com.company.repository.BookRepository;
import com.company.repository.entity.Book;
import com.company.service.BookService;
import com.company.service.dto.BookDto;
import com.company.service.dto.ObjectMapperService;
import com.company.service.exception.EntityNotFoundException;
import com.company.service.exception.ValidateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service("bookService")
@RequiredArgsConstructor
@Log4j2
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ObjectMapperService mapper;

    @Override
    public BookDto create(BookDto book) {
        log.debug("Create book={} in database book", book);
        validateCreate(book);
        Book bookCreated = mapper.toEntity(book);
        bookRepository.save(bookCreated);
        return mapper.toDto(bookCreated);
    }

    @Override
    public BookDto findById(Long id) {
        log.debug("Get book by id={} from database books", id);
        Book book = bookRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Book with id: " + id + " wasn't found"));
        if (book == null) {
            throw new EntityNotFoundException("Book with id:" + id + " doesn't exist");
        }

        return mapper.toDto(book);
    }

    @Override
    public BookDto getByIsbn(String isbn) {
        log.debug("Get book by isbn={} from database books", isbn);
        Book book = bookRepository.findByIsbn(isbn);
        if (book == null) {
            throw new EntityNotFoundException("Book with isbn:" + isbn + " doesn't exist");
        }
        return mapper.toDto(book);
    }

    @Override
    public List<BookDto> getByAuthor(String author) {
        log.debug("Get book by author={} from database books", author);
        List<Book> books = bookRepository.findByAuthor(author);
        return books.stream().map(mapper::toDto).toList();
    }

    @Override
    public BookDto getBookTitle(String title) {
        log.debug("Get book by title={} from database books", title);
        Book book = bookRepository.findByBookName(title);
        if (book == null) {
            throw new EntityNotFoundException("There isn't book:" + title + " on bookstore");
        }
        return mapper.toDto(book);
    }

    @Override
    public Page<BookDto> findAll(Pageable pageable) {
        log.debug("Get all books from database books");
        Page<Book> books = bookRepository.findAll(pageable);
        return books.map(mapper::toDto);
    }

    @Override
    public List<BookDto> findAll() {
        log.debug("Get all books from database books");
        List<Book> books = bookRepository.findAll();
        return books.stream().map(mapper::toDto).toList();
    }

    @Override
    public BookDto update(BookDto book) {
        log.debug("Update book={} in database books", book);
        validateUpdate(book);
        Book bookUpdated = mapper.toEntity(book);
        bookRepository.save(bookUpdated);
        return mapper.toDto(bookUpdated);
    }

    private void validateCreate(BookDto book) {
        if (book == null) {
            throw new ValidateException("Books can't be empty...");
        }
        if (book.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidateException("Price is not valid. Price can't be less 0");
        }
    }

    private void validateUpdate(BookDto book) {
        if (book.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidateException("Price is not valid. Price can't be less 0");
        }
    }

    @Override
    public void delete(Long id) {
        log.debug("Delete book by id={} from database books", id);
        bookRepository.deleteById(id);
    }

}