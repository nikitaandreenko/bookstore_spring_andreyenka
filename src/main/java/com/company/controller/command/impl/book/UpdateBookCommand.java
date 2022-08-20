package com.company.controller.command.impl.book;

import com.company.controller.command.Command;
import com.company.entity.Book;
import com.company.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller("update_book")
public class UpdateBookCommand implements Command {

    private final BookService bookService;

    @Autowired
    public UpdateBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        Book book = bookService.getById(id);
        book.setBook_name(req.getParameter("bookName"));
        book.setAuthor(req.getParameter("author"));
        book.setIsbn(req.getParameter("isbn"));
        book.setPrice(new BigDecimal(req.getParameter("price")));
        book.setPages(Integer.parseInt(req.getParameter("pages")));
        book.setBinding(req.getParameter("binding"));
        book.setYear_publishing(Integer.parseInt(req.getParameter("year_publishing")));
        book.setLanguage(Book.Language.valueOf(req.getParameter("language")));
        bookService.update(book);
        req.setAttribute("book", book);
        req.setAttribute("message", "bookstore by Andreyenka");
        req.setAttribute("messageUpdate", "Book successfully updated!!!");
        return "jsp/book/book.jsp";
    }
}
