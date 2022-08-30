package com.company.controller.command.impl.user;

import com.company.controller.command.Command;
import com.company.data.dto.UserDto;
import com.company.entity.User;
import com.company.service.UserService;
import com.company.service.dto.UserDtoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("create_user")
public class CreateUserCommand implements Command {
    private final UserService userService;

    @Autowired
    public CreateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {

        UserDtoService user = new UserDtoService();
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setAge(Integer.parseInt(req.getParameter("age")));
        user.setEmail(req.getParameter("email"));
        user.setRole(UserDtoService.Role.valueOf(req.getParameter("role")));
        userService.create(user);
        req.setAttribute("user", user);
        req.setAttribute("message", "bookstore by Andreyenka");
        req.setAttribute("messageCreate", "New user successfully added!!!");
        return "jsp/user/user.jsp";
    }
}
