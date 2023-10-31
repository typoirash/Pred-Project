package com.spring.controller;


import com.spring.model.User;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService us;
    @Autowired
    public UserController(UserService us) {
        this.us = us;
    }

    @GetMapping("/")
    public String allUsers(Model model) {
        model.addAttribute("users", us.allUser());
        return "users";
    }

    @GetMapping("/add")
    public String addUser(User user) {
        return "/create";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create";
        } else {
            us.addUser(user);
            return "redirect:/users/";
        }
    }

    @GetMapping("/user")
    public String getUser(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", us.getUserById(id));
        return "user";
    }

    @PostMapping("/user")
    public String get(User user) {
        return "redirect:/users/";
    }

    @GetMapping("/del")
    public String removeUser(@RequestParam("id") long id, RedirectAttributes ra) {
        us.removeUser(id);
        ra.addFlashAttribute("message", "The user ID:" + id + "has been deleted.");
        return "redirect:/users/";
    }

    @GetMapping("/edit")
    public String updateUser(@RequestParam("id") long id , Model model) {
        model.addAttribute("user", us.getUserById(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String update(User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        } else {
            us.updateUser(user);
            return "redirect:/users/";
        }
    }
}
