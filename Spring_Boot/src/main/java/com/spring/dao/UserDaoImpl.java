package com.spring.dao;

import com.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.spring.model.User;

import java.util.List;


@Component
public class UserDaoImpl implements UserDao {
    private final UserRepository userRepository;

    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void removeUser(long id) {
       userRepository.delete(getUserById(id));
    }

    @Override
    public User getUserById(long id) {
        return  userRepository.findById(id).get();
    }
    @Override
    public List<User> allUser() {
        return (List<User>) userRepository.findAll();
    }
}
