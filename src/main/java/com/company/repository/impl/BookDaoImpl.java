package com.company.repository.impl;

import com.company.repository.BookDao;
import com.company.entity.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("bookDao")
public class BookDaoImpl implements BookDao {

    private static final Logger log = LogManager.getLogger(BookDaoImpl.class);

    public static final String GET_ALL = "SELECT books.id, books.book_name, books.author, books.isbn, books.price, books.pages, " +
            "books.binding, books.year_publishing, languages.name " +
            "FROM books LEFT JOIN languages ON language_id = languages.id";
    public static final String GET_BY_ID = "SELECT books.id, books.book_name, books.author, books.isbn, books.price, books.pages, " +
            "books.binding, books.year_publishing, languages.name " +
            "FROM books JOIN languages ON language_id = languages.id WHERE books.id = ?";

    public static final String CREATE_BOOK = "INSERT INTO books (book_name, author, isbn, price, pages, binding," +
            "year_publishing, language_id) VALUES (?, ?, ?, ?, ?, ?, ?, (SELECT id FROM languages WHERE name = ?))";

    public static final String GET_BY_ISBN = "SELECT books.id, books.book_name, books.author, books.isbn, books.price, books.pages, " +
            "books.binding, books.year_publishing, languages.name " +
            "FROM books JOIN languages ON language_id = languages.id WHERE isbn = ?";
    public static final String UPDATE_NAMED = "UPDATE books SET book_name = :book_name, author = :author, isbn = :isbn, " +
            "price = :price, pages = :pages, binding = :binding, " +
            "year_publishing = :year_publishing, language_id = (SELECT id FROM languages WHERE name = :name) WHERE id = :id";
    public static final String GET_ALL_AUTHOR = "SELECT books.id, books.book_name, books.author, books.isbn, books.price, books.pages, " +
            "books.binding, books.year_publishing, languages.name " +
            "FROM books JOIN languages ON language_id = languages.id WHERE author =?";
    public static final String DELETE_BY_ID = "DELETE FROM books WHERE id=?";
    public static final String COUNT_ALL_BOOKS = "SELECT count(*) AS total FROM books";


    private final JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Book processRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setBook_name(rs.getString("book_name"));
        book.setAuthor(rs.getString("author"));
        book.setIsbn(rs.getString("isbn"));
        book.setPrice(rs.getBigDecimal("price"));
        book.setPages(rs.getInt("pages"));
        book.setBinding(rs.getString("binding"));
        book.setYear_publishing(rs.getInt("year_publishing"));
        book.setLanguage(Book.Language.valueOf(rs.getString("name")));
        return book;
    }

    @Autowired
    public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }


    @Override
    public Book create(Book book) {
        log.debug("Create book={} in database book", book);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(CREATE_BOOK, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getBook_name());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getIsbn());
            ps.setBigDecimal(4, book.getPrice());
            ps.setInt(5, book.getPages());
            ps.setString(6, book.getBinding());
            ps.setInt(7, book.getYear_publishing());
            ps.setString(8, book.getLanguage().toString());
            return ps;
        }, keyHolder);
        Long id = (Long) keyHolder.getKeys().get("id");
        if (id != null) {
            return findById(id);
        }
        return null;
    }

    @Override
    public Book findById(Long id) {
        log.debug("Get book by id={} from database books", id);
        try {
            return jdbcTemplate.queryForObject(GET_BY_ID, this::processRow, id);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public Book getByIsbn(String isbn) {
        log.debug("Get book by isbn={} from database books", isbn);
        try {
            return jdbcTemplate.queryForObject(GET_BY_ISBN, this::processRow, isbn);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public List<Book> findAll() {
        log.debug("Get all books from database books");
        return jdbcTemplate.query(GET_ALL, this::processRow);
    }


    @Override
    public List<Book> getByAuthor(String author) {
        log.debug("Get book by author={} from database books", author);
        return jdbcTemplate.query(GET_ALL_AUTHOR, this::processRow, author);
    }

    @Override
    public Long countAll() {
        log.debug("Count all books from database books");
        return jdbcTemplate.query(COUNT_ALL_BOOKS, (rs) -> {
            return rs.getLong("total");
        });
    }

    @Override
    public Book update(Book book) {
        log.debug("Update book={} in database books", book);
        Map<String, Object> map = new HashMap<>();
        map.put("book_name", book.getBook_name());
        map.put("author", book.getAuthor());
        map.put("isbn", book.getIsbn());
        map.put("price", book.getPrice());
        map.put("pages", book.getPages());
        map.put("binding", book.getBinding());
        map.put("year_publishing", book.getYear_publishing());
        map.put("name", book.getLanguage().toString());
        map.put("id", book.getId());
        namedJdbcTemplate.update(UPDATE_NAMED, map);
        return findById(book.getId());
    }

    @Override
    public boolean delete(Long id) {
        log.debug("Delete book by id={} from database books", id);
        int rowsUpdated = jdbcTemplate.update(DELETE_BY_ID, id);
        return rowsUpdated == 1;
    }
}
