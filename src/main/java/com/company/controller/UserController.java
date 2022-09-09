package com.company.controller;

import com.company.service.UserService;
import com.company.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        UserDto user = userService.findById(id);
        model.addAttribute("message", "bookstore by Andreyenka");
        model.addAttribute("messageUpdate", "User successfully updated!!!");
        model.addAttribute("messageCreate", "User successfully updated!!!");
        model.addAttribute("user", user);
        return "user/user";
    }

    @GetMapping("/getAll")
    public String getUsers(Model model) {
        List<UserDto> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("message", "bookstore by Andreyenka");
        return "user/users";
    }

    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("message", "bookstore by Andreyenka");
        return "user/createUserForm";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute UserDto user) {
        userService.create(user);
        return "redirect:/users/getAll";
    }

    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable Long id, Model model) {
        UserDto user = userService.findById(id);
        model.addAttribute("message", "bookstore by Andreyenka");
        model.addAttribute("user", user);
        return "user/updateUserForm";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute UserDto user, Model model) {
        userService.update(user);
        return "redirect:/users/" + user.getId();
    }

    @PostMapping("/delete/{id}")
    public String deleteUserForm(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users/getAll";
    }
}
