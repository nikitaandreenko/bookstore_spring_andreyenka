package com.company.controller.command.impl.user;

import com.company.controller.command.Command;
import com.company.entity.User;
import com.company.service.UserService;
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
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        int age = Integer.parseInt(req.getParameter("age"));
        String email = req.getParameter("email");
        User.Role role = User.Role.valueOf(req.getParameter("role"));
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setEmail(email);
        user.setRole(role);
        userService.create(user);
        req.setAttribute("user", user);
        req.setAttribute("message", "bookstore by Andreyenka");
        req.setAttribute("messageCreate", "New user successfully added!!!");
        return "jsp/user/user.jsp";
    }
}
