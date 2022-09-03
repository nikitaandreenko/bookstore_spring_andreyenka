package com.company.controller.command.impl.book;

import com.company.controller.command.Command;
import com.company.service.BookService;
import com.company.service.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
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
        BookDto book = new BookDto();
        book.setBookName(req.getParameter("bookName"));
        book.setAuthor(req.getParameter("author"));
        book.setIsbn(req.getParameter("isbn"));
        book.setPrice(new BigDecimal(req.getParameter("price")));
        book.setPages(Integer.parseInt(req.getParameter("pages")));
        book.setBinding(req.getParameter("binding"));
        book.setYearPublishing(Integer.parseInt(req.getParameter("year_publishing")));
        book.setLanguage(BookDto.Language.valueOf(req.getParameter("language")));
        bookService.create(book);
        req.setAttribute("book", book);
        req.setAttribute("message", "bookstore by Andreyenka");
        req.setAttribute("messageCreate", "New book successfully added!!!");
        return "jsp/book/book.jsp";
    }
}
