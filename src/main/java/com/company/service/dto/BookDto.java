package com.company.service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;
@Data
public class BookDto {
        private Long id;
        private String bookName;
        private String author;
        private String isbn;
        private BigDecimal price;
        private Integer pages;
        private String binding;
        private Integer yearPublishing;
        private Language language;
//        private String availability;

        public enum Language {
            ENGLISH, RUSSIAN, SPANISH, FRENCH, DEUTSCH, ARABIC, CHINESE, JAPANESE
        }

}
