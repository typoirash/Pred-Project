package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService us;

    public UserController(UserService us) {
        this.us = us;
    }

    @GetMapping("admin")
    public String allUsers(Model model, Principal principal) {
        //User idUser = us.getUserById(id);
        User user1 = us.getUserByUsername(principal.getName());
        model.addAttribute("user", user1);
        model.addAttribute("users", us.allUser());
        model.addAttribute("newUser", new User());
        //model.addAttribute("idUser", idUser);
        return "list_admin";
    }

    /*@GetMapping("admin/add")
    public String addUser(User user) {
        return "/create";
    }*/

    @PostMapping("admin/add")
    public String add(@ModelAttribute("newUser") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "list_admin";
        } else {
            us.addUser(user);
            return "redirect:/admin";
        }
    }

    /*@GetMapping("admin/user")
    public String getAdmin(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", us.getUserById(id));
        return "user-admin";
    }*/

    @GetMapping("user")
    public String getUser(Model model, Principal principal) {
        User user = us.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "list_user";
    }

    @PostMapping("admin/user")
    public String get(User user) {
        return "redirect:/admin/";
    }

    @GetMapping("admin/del")
    public String removeUser(@RequestParam("id") long id, RedirectAttributes ra) {
        us.removeUser(id);
        return "redirect:/admin";
    }

    /*@GetMapping("admin/edit")
    public String updateUsers(@RequestParam("id") long id , Model model) {
        User user = us.getUserById(id);
        model.addAttribute("user", user);
        return "edit";
    }*/

    @PostMapping("admin/edit")
    public String update(@ModelAttribute("newUser")@Valid User user, @RequestParam("id") long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "list_admin";
        } else {
            us.updateUser(id, user);
            return "redirect:/admin";
        }
    }
}
