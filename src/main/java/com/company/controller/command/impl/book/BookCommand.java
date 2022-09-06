package com.company.controller.command.impl.book;


import com.company.controller.command.Command;
import com.company.service.BookService;
import com.company.service.dto.BookDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

@Controller("book")
public class BookCommand implements Command {

    private final BookService bookService;

    @Autowired
    public BookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String idRaw = req.getParameter("id");
        Long id = Long.parseLong(idRaw);
        BookDto book = bookService.findById(id);
        req.setAttribute("book", book);
        req.setAttribute("message", "bookstore by Andreyenka");
        return "jsp/book/book.jsp";
    }
}
