package com.company.data.dao.impl;

import com.company.data.dao.BookDao;
import com.company.data.dto.BookDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BookDaoImpl implements BookDao {

    private static final Logger log = LogManager.getLogger(BookDaoImpl.class);

    public static final String GET_ALL = "SELECT books.id, books.book_name, books.author, books.isbn, books.price, books.pages, " +
            "books.binding, books.year_publishing, languages.name " +
            "FROM books LEFT JOIN languages ON language_id = languages.id WHERE books.deleted = FALSE";
    public static final String GET_BY_ID = "SELECT books.id, books.book_name, books.author, books.isbn, books.price, books.pages, " +
            "books.binding, books.year_publishing, languages.name " +
            "FROM books JOIN languages ON language_id = languages.id WHERE books.deleted = FALSE AND books.id = ?";

    public static final String CREATE_BOOK = "INSERT INTO books (book_name, author, isbn, price, pages, binding," +
            "year_publishing, language_id) VALUES (?, ?, ?, ?, ?, ?, ?, (SELECT id FROM languages WHERE name = ?))";

    public static final String GET_BY_ISBN = "SELECT books.id, books.book_name, books.author, books.isbn, books.price, books.pages, " +
            "books.binding, books.year_publishing, languages.name " +
            "FROM books JOIN languages ON language_id = languages.id WHERE books.deleted = FALSE AND isbn = ?";
    public static final String UPDATE_NAMED = "UPDATE books SET book_name = :book_name, author = :author, isbn = :isbn, " +
            "price = :price, pages = :pages, binding = :binding, " +
            "year_publishing = :year_publishing, language_id = (SELECT id FROM languages WHERE name = :name) WHERE deleted = FALSE AND id = :id";
    public static final String GET_ALL_AUTHOR = "SELECT books.id, books.book_name, books.author, books.isbn, books.price, books.pages, " +
            "books.binding, books.year_publishing, languages.name " +
            "FROM books JOIN languages ON language_id = languages.id WHERE books.deleted = FALSE AND author =?";
    public static final String DELETE_BY_ID = "UPDATE books SET deleted = TRUE WHERE id = ?";
    public static final String COUNT_ALL_BOOKS = "SELECT count(*) AS total FROM books WHERE deleted = FALSE AND";


    private final JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    private BookDto processRow(ResultSet rs, int rowNum) throws SQLException {
        BookDto bookDto = new BookDto();
        bookDto.setId(rs.getLong("id"));
        bookDto.setBook_name(rs.getString("book_name"));
        bookDto.setAuthor(rs.getString("author"));
        bookDto.setIsbn(rs.getString("isbn"));
        bookDto.setPrice(rs.getBigDecimal("price"));
        bookDto.setPages(rs.getInt("pages"));
        bookDto.setBinding(rs.getString("binding"));
        bookDto.setYear_publishing(rs.getInt("year_publishing"));
        bookDto.setLanguage(BookDto.Language.valueOf(rs.getString("name")));
        return bookDto;
    }

    @Override
    public BookDto create(BookDto bookDto) {
        log.debug("Create book={} in database book", bookDto);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(CREATE_BOOK, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, bookDto.getBook_name());
            ps.setString(2, bookDto.getAuthor());
            ps.setString(3, bookDto.getIsbn());
            ps.setBigDecimal(4, bookDto.getPrice());
            ps.setInt(5, bookDto.getPages());
            ps.setString(6, bookDto.getBinding());
            ps.setInt(7, bookDto.getYear_publishing());
            ps.setString(8, bookDto.getLanguage().toString());
            return ps;
        }, keyHolder);
        Long id = (Long) keyHolder.getKeys().get("id");
        if (id != null) {
            return findById(id);
        }
        return null;
    }

    @Override
    public BookDto findById(Long id) {
        log.debug("Get book by id={} from database books", id);
        try {
            return jdbcTemplate.queryForObject(GET_BY_ID, this::processRow, id);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public List<BookDto> findAll() {
        log.debug("Get all books from database books");
        return jdbcTemplate.query(GET_ALL, this::processRow);
    }

    @Override
    public Long countAll() {
        log.debug("Count all books from database books");
        return jdbcTemplate.query(COUNT_ALL_BOOKS, (rs) -> {
            return rs.getLong("total");
        });
    }

    @Override
    public BookDto update(BookDto bookDto) {
        log.debug("Update book={} in database books", bookDto);
        Map<String, Object> map = new HashMap<>();
        map.put("book_name", bookDto.getBook_name());
        map.put("author", bookDto.getAuthor());
        map.put("isbn", bookDto.getIsbn());
        map.put("price", bookDto.getPrice());
        map.put("pages", bookDto.getPages());
        map.put("binding", bookDto.getBinding());
        map.put("year_publishing", bookDto.getYear_publishing());
        map.put("name", bookDto.getLanguage().toString());
        map.put("id", bookDto.getId());
        namedJdbcTemplate.update(UPDATE_NAMED, map);
        return findById(bookDto.getId());
    }

    @Override
    public boolean delete(Long id) {
        log.debug("Delete book by id={} from database books", id);
        int rowsUpdated = jdbcTemplate.update(DELETE_BY_ID, id);
        return rowsUpdated == 1;
    }

    @Override
    public BookDto getByIsbn(String isbn) {
        log.debug("Get book by isbn={} from database books", isbn);
        try {
            return jdbcTemplate.queryForObject(GET_BY_ISBN, this::processRow, isbn);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public List<BookDto> getByAuthor(String author) {
        log.debug("Get book by author={} from database books", author);
        return jdbcTemplate.query(GET_ALL_AUTHOR, this::processRow, author);
    }
}
