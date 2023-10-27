package org.crud.service;

import org.crud.model.User;

import java.util.List;

public interface UserSERVICE {
    void addUser(User user);
    void updateUser(User user);
    void removeUser(long id);
    User getUserById(long id);
    /*void removeUser(int id);
    User getUserById(int id);*/
    List<User> allUser();

}
