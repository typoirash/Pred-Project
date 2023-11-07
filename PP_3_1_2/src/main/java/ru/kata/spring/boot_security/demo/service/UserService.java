package ru.kata.spring.boot_security.demo.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.securiti.UserDetailsServiceImpl;

import java.lang.annotation.Native;
import java.util.List;

public interface UserService {
    //void updateUser(User user);
    //List<Role> getRoles();
    //@Query(name = "insert into users_roles (user_id, roles_id) values ()")
    //User getUserByUsername();
    User getUserByUsername(String name);

    void updateUser(User user);
   /* @Query("INSERT INTO users_roles(user_id, roles_id) values (:user_id, :role_id)")
    void updateAdmin(long id);
*/
    void removeUser(long id);
    User getUserById(long id);
    void addUser(User user);
    List<User> allUser();

}
