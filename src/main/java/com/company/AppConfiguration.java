package com.company;

import com.company.controller.command.impl.book.AllBookCommand;
import com.company.controller.command.impl.book.BookCommand;
import com.company.controller.command.impl.error.ErrorCommand;
import com.company.controller.command.impl.user.AllUserCommand;
import com.company.controller.command.impl.user.CreateUserCommand;
import com.company.controller.command.impl.user.UserCommand;
import com.company.dao.connection.DateSourсe;
import com.company.dao.impl.BookDaoImpl;
import com.company.dao.impl.UserDaoImpl;
import com.company.service.impl.BookServiceImpl;
import com.company.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public DateSourсe dateSourсe(){
        return DateSourсe.INSTANCE;
    }

    @Bean
    public BookDaoImpl bookDao(){
        return new BookDaoImpl(dateSourсe());
    }
    @Bean
    public UserDaoImpl userDao(){
        return new UserDaoImpl(dateSourсe());
    }
    @Bean
    public BookServiceImpl bookService(){
        return new BookServiceImpl(bookDao());
    }
    @Bean
    public UserServiceImpl userService(){
        return new UserServiceImpl(userDao());
    }
    @Bean
    public BookCommand book (){
        return new BookCommand(bookService());
    }
    @Bean
    public AllBookCommand all_books (){
        return new AllBookCommand(bookService());
    }
    @Bean
    public AllUserCommand all_users (){
        return new AllUserCommand(userService());
    }
    @Bean
    public UserCommand user (){
        return new UserCommand(userService());
    }

    @Bean
    public CreateUserCommand create_user (){
        return new CreateUserCommand(userService());
    }
    @Bean
    public ErrorCommand error(){
        return new ErrorCommand();
    }

}
