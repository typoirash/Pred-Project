package org.crud.controller;

import org.crud.model.User;
import org.crud.service.UserSERVICE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
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
        us.addUser(user);
        return "/create";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("user") User user) {
        if (user.getId() == 0) {
            us.addUser(user);
            return "redirect:/";
        } else {
            return "create";
        }
    }

    @GetMapping("/{id}")
    public String getUser(@RequestParam(value = "id") long id, Model model) {
        model.addAttribute("user", us.getUserById(id));
        return "user";
    }

    @GetMapping("/del/{id}")
    public String removeUser(@RequestParam(value = "id") long id) {
        us.removeUser(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String updateUser(@RequestParam(value = "id") long id, Model model) {
        model.addAttribute("user", us.getUserById(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String update(User user) {
        if (user.getId() == 0) {
            return "edit";
        } else {
            us.updateUser(user);
            return "redirect:/";
        }
    }
}
