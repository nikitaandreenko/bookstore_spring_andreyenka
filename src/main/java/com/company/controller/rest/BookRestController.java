package com.company.controller.rest;

import com.company.controller.rest.exception_handling.EntityIncorrectData;
import com.company.controller.rest.exception_handling.NoSuchEntityException;
import com.company.service.BookService;
import com.company.service.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor                                         
@RequestMapping("/api")
public class BookRestController {

    private final BookService bookService;

    @GetMapping("/books")
    public List<BookDto> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/books/{id}")
    public BookDto getBook(@PathVariable Long id) {
        BookDto bookDto = bookService.findById(id);
        if (bookDto == null) {
            throw new NoSuchEntityException("There is no book with id = " + id + " in database");
        }
        return bookDto;
    }

    @PostMapping("/books")
    public BookDto addBook(@RequestBody BookDto bookDto) {
        BookDto bookDto1 = bookService.create(bookDto);
        return bookDto1;
    }

    @PutMapping("/books")
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        BookDto bookDto1 = bookService.update(bookDto);
        return bookDto1;
    }

    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable Long id) {
        BookDto bookDto = bookService.findById(id);
        if (bookDto == null) {
            throw new NoSuchEntityException("There is no book with id = " + id + " in database");
        }
        bookService.delete(id);
        return "Book with id = " + id + " was deleted";
    }

    @ExceptionHandler
    public ResponseEntity<EntityIncorrectData> handleException(NoSuchEntityException exception) {
        EntityIncorrectData data = new EntityIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EntityIncorrectData> handleException(Exception exception) {
        EntityIncorrectData data = new EntityIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}