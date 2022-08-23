package com.company.controller.command.impl.book;

import com.company.controller.command.Command;
import com.company.entity.Book;
import com.company.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("update_book_form")
public class UpdateBookFormCommand implements Command {
    private final BookService bookService;

    @Autowired
    public UpdateBookFormCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        Book book = bookService.getById(id);
        req.setAttribute("book", book);
        req.setAttribute("message", "bookstore by Andreyenka");
        return "jsp/book/update_book.jsp";
    }
}
