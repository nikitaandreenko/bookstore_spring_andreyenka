package com.company.controller;

import com.company.service.BookService;

import com.company.service.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        BookDto book = bookService.findById(id);
        model.addAttribute("message", "bookstore by Andreyenka");
        model.addAttribute("book", book);
        return "book/book";
    }

    @GetMapping("/getAll")
    public String getBooks(Model model) {
        List<BookDto> books = bookService.findAll();
        model.addAttribute("books", books);
        model.addAttribute("message", "bookstore by Andreyenka");
        return "book/books";
    }

    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("message", "bookstore by Andreyenka");
        return "book/createBookForm";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute BookDto book) {
        bookService.create(book);
        return "redirect:/books/" + book.getId();
    }

    @GetMapping("/update/{id}")
    public String updateBookForm(@PathVariable Long id, Model model) {
        BookDto book = bookService.findById(id);
        model.addAttribute("message", "bookstore by Andreyenka");
        model.addAttribute("book", book);
        return "book/updateBookForm";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@ModelAttribute BookDto book) {
        bookService.update(book);
        return "redirect:/books/" + book.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteBookForm(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books/getAll";
    }
}