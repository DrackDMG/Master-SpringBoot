package com.drack.MovieManagement.controller;

import com.drack.MovieManagement.exception.ObjectNotFoundException;
import com.drack.MovieManagement.percistence.entity.Movie;
import com.drack.MovieManagement.percistence.entity.User;
import com.drack.MovieManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll(@RequestParam(required = false) String name) {
        List<User> users = null;
        if(StringUtils.hasText(name)){
            users = userService.findAllByName(name);
        }else {
            users = userService.findAll();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        try {
            return ResponseEntity.ok(userService.findOneByUsername(username));
        }catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
