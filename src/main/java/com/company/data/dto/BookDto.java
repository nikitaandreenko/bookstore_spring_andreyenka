package com.company.data.dto;

import com.company.entity.Book;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDto {
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
