package com.drack.MovieManagement.service.impl;

import com.drack.MovieManagement.exception.ObjectNotFoundException;
import com.drack.MovieManagement.percistence.entity.User;
import com.drack.MovieManagement.percistence.repository.UserRepository;
import com.drack.MovieManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllByName(String name) {
        return userRepository.findByNameContaining(name);
    }

    @Override
    public User findOneByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("[user:" + username + "]"));
    }

    @Override
    public User createOne(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateOneByUsername(String username, User user) {
        User oldUser = this.findOneByUsername(username);
        oldUser.setName(user.getName());
        oldUser.setPassword(user.getPassword());
        return userRepository.save(oldUser);
    }

    @Override
    public void deleteOneByUsername(String username) {
        //User user = this.findOneByUsername(username);
      //  userRepository.delete(user);
         if (userRepository.deleteByUsername(username) != 1) {
             throw new ObjectNotFoundException("[user:" + username + "]");
         }
    }
}
