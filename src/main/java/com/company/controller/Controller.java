package com.company.controller;

import com.company.AppConfiguration;
import com.company.controller.command.Command;
import com.company.controller.command.impl.error.ErrorCommand;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private AnnotationConfigApplicationContext context;

    @Override
    public void init() throws ServletException {
        context = new AnnotationConfigApplicationContext(AppConfiguration.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandParam = req.getParameter("command");
        Command command;
        if (context.getBean(commandParam) == null) {
            command = context.getBean(ErrorCommand.class);
        } else {
            command = (Command) context.getBean(commandParam);
        }
        String page;
        try {
            page = command.execute(req);
        } catch (Exception e) {
            req.setAttribute("message", "My friend please write correctly what you want to see in my store");
            page = "jsp/error.jsp";
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    public void destroy() {
        context.close();
        context.getBean(EntityManagerFactory.class).close();
    }
}

