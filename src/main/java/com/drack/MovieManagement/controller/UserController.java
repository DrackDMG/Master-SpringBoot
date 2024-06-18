package com.drack.MovieManagement.controller;

import com.drack.MovieManagement.percistence.entity.Movie;
import com.drack.MovieManagement.percistence.entity.User;
import com.drack.MovieManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAll(@RequestParam(required = false) String name) {
        List<User> users = null;
        if(StringUtils.hasText(name)){
            users = userService.findAllByName(name);
        }else {
            users = userService.findAll();
        }
        return users;
    }

    @GetMapping("/{username}")
    public User findByUsername(@PathVariable String username) {
        return userService.findOneByUsername(username);
    }


}
