package com.company.controller.command.impl.book;

import com.company.controller.command.Command;
import com.company.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("create_book_form")
public class CreateBookFormCommand implements Command {

    private final BookService bookService;

    @Autowired
    public CreateBookFormCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        req.setAttribute("message", "bookstore by Andreyenka");
        return "jsp/book/create_book.jsp";
    }
}
