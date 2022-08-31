package com.company.controller.command.impl.book;

import com.company.controller.command.Command;
import com.company.service.BookService;
import com.company.service.dto.BookDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("all_books")
public class AllBookCommand implements Command {
    private final BookService bookService;

    @Autowired
    public AllBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        List<BookDto> books = bookService.findAll();
        req.setAttribute("all_books", books);
        req.setAttribute("message", "bookstore by Andreyenka");
        return "jsp/book/all_books.jsp";
    }
}
