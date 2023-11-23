package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.exceptions.UserAlreadyExistException;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUsername(String name) {
        return userRepository.findByName(name).orElse(null);
    }



    @Override
    public void addUser(User user) throws UserAlreadyExistException {

        if (userRepository.findByName(user.getName()).isPresent()) {
            throw new UserAlreadyExistException("A user with this name already exists");
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            if (user.getRoles().hashCode() == 0) {
                user.setRoles(Collections.singleton(new Role(2L,"USER")));
            }
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }


    }

    @Override
    public void updateUser(User user) {
        User oldUser = getUserById(user.getId());
        if (user.getRoles().hashCode() == 0) {
            user.setRoles(oldUser.getRoles());
        }
        if (user.getPassword().hashCode() == oldUser.getPassword().hashCode()) {
            user.setPassword(user.getPassword());
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    @Override
    public void removeUser(long id) {
        userRepository.delete(getUserById(id));
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    public List<User> allUser() {
        List<User> users = userRepository.findAll();

        return users;
    }

    @Override
    public List<String> allRoles() {
        List<User> users = userRepository.findAll();
        List<String> roles = new ArrayList<>();
        int i = 0;
        for (User us : users) {
            String role = us.getUserRole();
            roles.set(i,role);
            i++;
        }
        return roles;
    }
}
