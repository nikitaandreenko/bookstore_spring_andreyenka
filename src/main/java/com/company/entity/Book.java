package com.company.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Book {
    private Long id;
    private String book_name;
    private String author;
    private String isbn;
    private BigDecimal price;
    private Integer pages;
    private String binding;
    private Integer year_publishing;

    private Language language;

    public enum Language {
        ENGLISH, RUSSIAN, SPANISH, FRENCH, DEUTSCH, ARABIC, CHINESE, JAPANESE
    }

}
