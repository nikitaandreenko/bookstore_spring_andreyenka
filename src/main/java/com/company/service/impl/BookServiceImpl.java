package com.company.service.impl;

import com.company.repository.BookDao;
import com.company.repository.impl.BookDaoImpl;
import com.company.entity.Book;
import com.company.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {
    private static final Logger log = LogManager.getLogger(BookServiceImpl.class);

    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book create(Book book) {
        log.debug("Create book={} in database book", book);
        validate(book);
        return bookDao.create(book);
    }

    @Override
    public Book findById(Long id) {
        log.debug("Get book by id={} from database books", id);
        Book book = bookDao.findById(id);
        if (book == null) {
            throw new RuntimeException("Book with id:" + id + " doesn't exist");
        }
        return book;
    }

    @Override
    public Book getByIsbn(String isbn) {
        log.debug("Get book by isbn={} from database books", isbn);
        Book book = bookDao.getByIsbn(isbn);
        if (book == null) {
            throw new RuntimeException("Book with isbn:" + isbn + " doesn't exist");
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        log.debug("Get all books from database books");
        return bookDao.findAll();
    }

    @Override
    public List<Book> getByAuthor(String author) {
        log.debug("Get book by author={} from database books", author);
        return bookDao.getByAuthor(author);
    }

    @Override
    public Long countAll() {
        log.debug("Count all books from database books");
        return bookDao.countAll();
    }

    @Override
    public Book update(Book book) {
        log.debug("Update book={} in database books", book);
        validate(book);
        Book book1 = bookDao.update(book);
        if (book1 == null) {
            throw new RuntimeException("Books can't be empty...");
        }
        return book1;
    }

    private void validate(Book book) {
        if (book.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Price is not valid. Price can't be less 0");
        }
    }

    @Override
    public void delete(Long id) {
        log.debug("Delete book by id={} from database books", id);
        boolean successRemove = bookDao.delete(id);
        if (!successRemove) {
            throw new RuntimeException("This book doesn't remove");
        }
    }

    @Override
    public BigDecimal totalPriceByAuthor(String author) {
        log.debug("Total books by author={} from database books", author);
        List<Book> books = getByAuthor(author);
        BigDecimal total = books.stream().map(Book::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        return total;
    }
}
