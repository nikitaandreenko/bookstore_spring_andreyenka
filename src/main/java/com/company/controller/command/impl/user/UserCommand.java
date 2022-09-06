package com.company.controller.command.impl.user;

import com.company.controller.command.Command;
import com.company.service.UserService;
import com.company.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

@Controller("user")
public class UserCommand implements Command {

    private final UserService userService;

    @Autowired
    public UserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String idRaw = req.getParameter("id");
        Long id = Long.parseLong(idRaw);
        UserDto user = userService.findById(id);
        req.setAttribute("user", user);
        req.setAttribute("message", "bookstore by Andreyenka");
        return "jsp/user/user.jsp";
    }
}
