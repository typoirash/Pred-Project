package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;

@Controller
public class StartController
{
    private final UserService userService;

    public StartController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/user")
//    public String userMain() {
//        return "list_user";
//    }

    @GetMapping("/")
    public String adminMain(Model model, Principal principal) {
        User user1 = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user1);
        model.addAttribute("users", userService.allUser());
        model.addAttribute("newUser", new User());
        return "list_admin";
    }
    /*@GetMapping("/user")
    public String userMain(Model model, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "list_user";
    }*/
}
