package com.company.controller.command.impl.book;

import com.company.controller.command.Command;
import com.company.entity.Book;
import com.company.entity.User;
import com.company.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller("create_book")
public class CreateBookCommand implements Command {
    private final BookService bookService;

    @Autowired
    public CreateBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Book book = new Book();
        book.setBook_name(req.getParameter("bookName"));
        book.setAuthor(req.getParameter("author"));
        book.setIsbn(req.getParameter("isbn"));
        book.setPrice(new BigDecimal(req.getParameter("price")));
        book.setPages(Integer.parseInt(req.getParameter("pages")));
        book.setBinding(req.getParameter("binding"));
        book.setYear_publishing(Integer.parseInt(req.getParameter("year_publishing")));
        book.setLanguage(Book.Language.valueOf(req.getParameter("language")));
        bookService.create(book);
        req.setAttribute("book", book);
        req.setAttribute("message", "bookstore by Andreyenka");
        req.setAttribute("message2", "New book successfully added!!!");
        return "jsp/book/book.jsp";
    }
}
