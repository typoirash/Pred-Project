package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.exceptions.UserAlreadyExistException;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;

public interface UserService {
    List<String> allRoles();
    User getUserByUsername(String name);
    void updateUser(User user);
    void removeUser(long id);
    User getUserById(long id);
    void addUser(User user) throws UserAlreadyExistException;
    List<User> allUser();
}
