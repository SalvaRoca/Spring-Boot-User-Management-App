package com.rocode.springbootcourse.controllers;

import com.rocode.springbootcourse.dao.UserDao;
import com.rocode.springbootcourse.models.User;
import com.rocode.springbootcourse.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    private boolean validateToken (String token) {
        String userId = jwtUtil.getKey(token);
        return userId != null;
    }

    @GetMapping(value = "api/users")
    public List<User> getAllUsers(@RequestHeader(value= "Authorization") String token) {
        if (!validateToken(token)) {
            return null;
        }
        return  userDao.getAllUsers();
    }

    @GetMapping(value = "api/user/{id}")
    public User getUser(@RequestHeader(value= "Authorization") String token, @PathVariable Long id)  {
        if (!validateToken(token)) {
            return null;
        }
        return userDao.getUser(id);
    }

    @PostMapping(value = "api/users")
    public void postUser(@RequestBody User user) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);
        userDao.postUser(user);
    }

    @DeleteMapping(value = "api/user/{id}")
    public void deleteUser(@RequestHeader(value= "Authorization") String token, @PathVariable Long id) {
        if (validateToken(token)) {
            userDao.delete(id);
        }
    }
}
