package com.company.controller.command.impl.user;

import com.company.controller.command.Command;
import com.company.entity.User;
import com.company.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("update_user_form")
public class UpdateUserFormCommand implements Command {

    private final UserService userService;

    @Autowired
    public UpdateUserFormCommand(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        User user = userService.findById(id);
        req.setAttribute("user", user);
        req.setAttribute("message", "bookstore by Andreyenka");
        return "jsp/user/update_user.jsp";
    }
}
