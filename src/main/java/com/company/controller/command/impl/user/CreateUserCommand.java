package com.company.controller.command.impl.user;

import com.company.controller.command.Command;
import com.company.service.UserService;
import com.company.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

@Controller("create_user")
public class CreateUserCommand implements Command {
    private final UserService userService;

    @Autowired
    public CreateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {

        UserDto user = new UserDto();
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setAge(Integer.parseInt(req.getParameter("age")));
        user.setEmail(req.getParameter("email"));
        user.setRole(UserDto.Role.valueOf(req.getParameter("role")));
        userService.create(user);
        req.setAttribute("user", user);
        req.setAttribute("message", "bookstore by Andreyenka");
        req.setAttribute("messageCreate", "New user successfully added!!!");
        return "jsp/user/user.jsp";
    }
}
