package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    // @PersistenceContext
    // private EntityManager entityManager;
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
    public void addUser(User user) {
        //User userDB = userRepository.findByName(user.getName()).get();
        /*User userFromDB = userRepository.findByName(user.getName());

        if (userFromDB != null) {

        } else {
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }*/
        user.setRoles(Collections.singleton(new Role(2L)));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    /*public List<Role> getRoles() {
        return roleRepository.findAll();
    }
*/
    @Override
    public void updateUser(User user) {
        /*User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {

        } else {
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }*/
        //User oldUser = user;

        user.setRoles(Collections.singleton(new Role(2L)));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    /*public boolean deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }*/
    @Override
    public void removeUser(long id) {
        userRepository.delete(getUserById(id));
    }

    /*public boolean getUserByUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return true;
        } else {
            return false;
        }

    }*/
    @Override
    public User getUserById(long id) {
        //return userRepository.findById(id).get();
        return userRepository.findById(id).orElse(new User());
    }


    @Override
    public List<User> allUser() {
        return userRepository.findAll();
    }
}
