package com.company.repository.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "books")
@SQLDelete(sql = "update books set availability='out of stock' where id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

    @Column(name = "availability")
    private String availability = "in stock";

    @OneToMany(mappedBy = "book")
    private List<OrderItem> orderItems;

    public enum Language {
        ENGLISH, RUSSIAN, SPANISH, FRENCH, DEUTSCH, ARABIC, CHINESE, JAPANESE
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(bookName, book.bookName) && Objects.equals(author, book.author) && Objects.equals(isbn, book.isbn) && Objects.equals(price, book.price) && Objects.equals(pages, book.pages) && Objects.equals(binding, book.binding) && Objects.equals(yearPublishing, book.yearPublishing) && language == book.language && Objects.equals(availability, book.availability) && Objects.equals(orderItems, book.orderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookName, author, isbn, price, pages, binding, yearPublishing, language, availability, orderItems);
    }
}

