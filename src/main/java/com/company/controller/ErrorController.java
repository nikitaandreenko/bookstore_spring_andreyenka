package com.company.controller;

import com.company.service.exception.EntityNotFoundException;
import com.company.service.exception.ValidateException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
@ControllerAdvice("com.company")
@RequestMapping("/error")
public class ErrorController {

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String error(Model model, EntityNotFoundException e) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String error(Model model, Exception e) {
        model.addAttribute("message", "My friend please drink water! You are asking for the impossible");
        return "error";
    }
    @ExceptionHandler
    public String error(Model model, ValidateException e) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}
