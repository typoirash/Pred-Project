package org.crud.controller;

import org.crud.model.User;
import org.crud.service.UserSERVICE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserSERVICE us;
    @Autowired
    public UserController(UserSERVICE us) {
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

    @GetMapping
    public String getUser(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", us.getUserById(id));
        return "user";
    }

    @GetMapping("/del")
    public String removeUser(@RequestParam("id") long id ) {
        us.removeUser(id);
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
