package com.company.controller.command.impl.user;

import com.company.controller.command.Command;
import com.company.entity.User;
import com.company.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("delete_user")
public class DeleteUserCommand implements Command {
    private final UserService userService;

    @Autowired
    public DeleteUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        userService.delete(id);
        req.setAttribute("message", "bookstore by Andreyenka");
        req.setAttribute("message2", "User successfully deleted!!!");
        return "jsp/user/delete_user.jsp";

    }
}
