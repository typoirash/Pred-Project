package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private UserService us;

    @GetMapping("/registration")
    public String addUser(User user) {
        return "/create";
    }

    @PostMapping("/registration")
    public String add(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "create";
        }
        /*if (!user.getPassword().equals(user.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "create";
        }
        if (!us.addUser(user)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "create";
        }*/
        return "redirect:/";
    }
}
