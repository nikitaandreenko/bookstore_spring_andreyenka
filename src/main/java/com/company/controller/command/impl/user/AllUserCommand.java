package com.company.controller.command.impl.user;

import com.company.controller.command.Command;
import com.company.entity.User;
import com.company.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("all_users")
public class AllUserCommand implements Command {

    private final UserService userService;

    @Autowired
    public AllUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        List<User> users = userService.getAll();
        req.setAttribute("all_users", users);
        req.setAttribute("message", "bookstore by Andreyenka");
        return "jsp/user/all_users.jsp";
    }
}
