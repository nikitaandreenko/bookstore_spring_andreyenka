package com.company.controller;

import com.company.repository.entity.Book;
import com.company.service.BookService;

import com.company.service.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        model.addAttribute("book", book);
        return "book/book";
    }

    @GetMapping("/getAll")
    public String getBooks(Model model, @RequestParam(required = false) Integer page) {
        if(page == null){
            page = 0;
        }else{
            page--;
        }
        Pageable pageable = PageRequest.of(page, 5, Sort.Direction.ASC, "id");
        Page <BookDto> books = bookService.findAll(pageable);
        model.addAttribute("books", books.toList());
        model.addAttribute("currentPage", page+1);
        model.addAttribute("totalPages", books.getTotalPages());
        return "book/books";
    }

    @GetMapping("/create")
    public String createUserForm(Model model) {
        return "book/createBookForm";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute BookDto book) {
        bookService.create(book);
        return "redirect:/books/getAll";
    }

    @GetMapping("/update/{id}")
    public String updateBookForm(@PathVariable Long id, Model model) {
        BookDto book = bookService.findById(id);
        model.addAttribute("book", book);
        return "book/updateBookForm";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@ModelAttribute BookDto book) {
        bookService.update(book);
        return "redirect:/books/" + book.getId();
    }

    @PostMapping("/delete/{id}")
    public String deleteBookForm(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books/getAll";
    }

    @GetMapping("/getAllByAuthor")
    public  String getAllBookByAuthor(@RequestParam String author, Model model){
        List<BookDto> books = bookService.getByAuthor(author);
        model.addAttribute("books", books);
            return "book/books";
        }
    @GetMapping("/getBookTitle")
    public  String getBookByTitle(@RequestParam String title, Model model){
        BookDto book = bookService.getBookTitle(title);
        model.addAttribute("book", book);
        return "book/book";
    }
}
