package com.company.controller.command.impl.user;

import com.company.controller.command.Command;

import com.company.service.UserService;
import com.company.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
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
        List<UserDto> users = userService.findAll();
        req.setAttribute("all_users", users);
        req.setAttribute("message", "bookstore by Andreyenka");
        return "jsp/user/all_users.jsp";
    }
}
