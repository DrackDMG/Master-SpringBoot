package com.drack.MovieManagement.service;

import com.drack.MovieManagement.percistence.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    List<User> findAllByName(String name);
    User findOneByUsername(String username);
    User createOne(User user);
    User updateOneByUsername(String username, User user);
    void deleteOneByUsername(String username);
}
