package com.company.controller.command.impl.book;

import com.company.controller.command.Command;
import com.company.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

@Controller("delete_book")
public class DeleteBookCommand implements Command {

    private final BookService bookService;

    @Autowired
    public DeleteBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        bookService.delete(id);
        req.setAttribute("message", "bookstore by Andreyenka");
        req.setAttribute("messageDelete", "Book successfully deleted!!!");
        return "jsp/book/delete_book.jsp";
    }
}
