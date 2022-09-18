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
        model.addAttribute("user", user);
        return "user/user";
    }

    @GetMapping("/getAll")
    public String getUsers(Model model) {
        List<UserDto> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/users";
    }

    @GetMapping("/create")
    public String createUserForm(Model model) {
        return "user/createUserForm";
    }

    @GetMapping("/registration")
    public String registrationUserForm(Model model) {
        return "user/registrationForm";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute UserDto user) {
        UserDto dto = userService.create(user);
        return "redirect:/users/" + dto.getId();
    }

    @PostMapping("/registration")
    public String registrationUser(@ModelAttribute UserDto user) {
        userService.registration(user);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable Long id, Model model) {
        UserDto user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/updateUserForm";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute UserDto user, Model model) {
        userService.update(user);
        return "redirect:/users/" + user.getId();
    }
    @PostMapping("/updateRegistration/{id}")
    public String updateRegistrationUser(@ModelAttribute UserDto user, Model model) {
        userService.updateRegistration(user);
        return "redirect:/users/" + user.getId();
    }

    @PostMapping("/delete/{id}")
    public String deleteUserForm(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users/getAll";
    }
}
