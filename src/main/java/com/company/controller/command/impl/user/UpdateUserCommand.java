package com.company.controller.command.impl.user;

import com.company.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class UpdateUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return null;
    }
}
