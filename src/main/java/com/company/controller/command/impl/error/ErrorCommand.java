package com.company.controller.command.impl.error;

import com.company.controller.command.Command;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

@Controller("error")
public class ErrorCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        req.setAttribute("message", "My friend please write correctly what you want to see in my store");
        return "jsp/error.jsp";
    }
}
