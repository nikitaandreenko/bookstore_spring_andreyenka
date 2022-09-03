package com.company.controller.command.impl.user;

import com.company.controller.command.Command;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

@Controller("create_user_form")
public class CreateUserFormCommand implements Command {

    private final UserService userService;

    @Autowired
    public CreateUserFormCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        req.setAttribute("message", "bookstore by Andreyenka");
        return "jsp/user/create_user.jsp";
    }
}
