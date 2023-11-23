package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import ru.kata.spring.boot_security.demo.exceptions.UserAlreadyExistException;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static org.springframework.web.servlet.function.ServerResponse.badRequest;

@EnableMethodSecurity
@RestController
@RequestMapping("/")
public class UserController {
    private final UserService us;

    public UserController(UserService us) {
        this.us = us;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<User>> allUsers(Principal principal) {
        User user = us.getUserByUsername(principal.getName());
        try {
            return new ResponseEntity<>( us.allUser(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("admin/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> add(@RequestBody @Valid User user, String name, BindingResult bindingResult) throws UserAlreadyExistException {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Input error", HttpStatus.BAD_REQUEST); //"Input error"
        }
        us.addUser(user);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("user/user")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<User> getUser(Principal principal) {
        User user = us.getUserByUsername(principal.getName());
        try {
            return new ResponseEntity<>(user,HttpStatus.OK);
        } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("admin/user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> get(User user) {
        try {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("admin/del/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> removeUser(@PathVariable("id") long id) {
        us.removeUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("admin/edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> update(@PathVariable("id") long id,
                                       @RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            us.updateUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

}
