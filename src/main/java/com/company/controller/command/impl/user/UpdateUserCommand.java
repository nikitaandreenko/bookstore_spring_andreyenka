package com.company.controller.command.impl.user;

import com.company.controller.command.Command;
import com.company.service.UserService;
import com.company.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

@Controller("update_user")
public class UpdateUserCommand implements Command {

    private final UserService userService;

    @Autowired
    public UpdateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        UserDto user = userService.findById(id);
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setAge(Integer.parseInt(req.getParameter("age")));
        user.setEmail(req.getParameter("email"));
        user.setRole((UserDto.Role.valueOf(req.getParameter("role"))));
        UserDto updated = userService.update(user);
        req.setAttribute("user", updated);
        req.setAttribute("message", "bookstore by Andreyenka");
        req.setAttribute("messageUpdate", "User successfully updated!!!");
        return "jsp/user/user.jsp";

    }
}
