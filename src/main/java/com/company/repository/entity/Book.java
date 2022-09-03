package com.company.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "books")
@SQLDelete(sql = "update books set deleted=true where id=?")
@Where(clause = "deleted = false")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "author")
    private String author;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "binding")
    private String binding;

    @Column(name = "year_publishing")
    private Integer yearPublishing;

    @Column(name = "book_language")
    @Enumerated(EnumType.STRING)
    private Language language;

    @Column(name = "deleted")
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "book")
    private List<OrderItem> orderItems;

    public enum Language {
        ENGLISH, RUSSIAN, SPANISH, FRENCH, DEUTSCH, ARABIC, CHINESE, JAPANESE
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public Integer getYearPublishing() {
        return yearPublishing;
    }

    public void setYearPublishing(Integer yearPublishing) {
        this.yearPublishing = yearPublishing;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(bookName, book.bookName) && Objects.equals(author, book.author) && Objects.equals(isbn, book.isbn) && Objects.equals(price, book.price) && Objects.equals(pages, book.pages) && Objects.equals(binding, book.binding) && Objects.equals(yearPublishing, book.yearPublishing) && language == book.language && Objects.equals(isDeleted, book.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookName, author, isbn, price, pages, binding, yearPublishing, language, isDeleted);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", price=" + price +
                ", pages=" + pages +
                ", binding='" + binding + '\'' +
                ", yearPublishing=" + yearPublishing +
                ", language=" + language +
                ", isDeleted=" + isDeleted +
                '}';
    }
}

