package com.rocode.springbootcourse.controllers;

import com.rocode.springbootcourse.dao.UserDao;
import com.rocode.springbootcourse.models.User;
import com.rocode.springbootcourse.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping(value = "api/login")
    public String userLogin(@RequestBody User user) {
        User loggedUser = userDao.getUserByCredentials(user);
        if (loggedUser != null) {
            return jwtUtil.create(String.valueOf(loggedUser.getId()), loggedUser.getEmail());
        }
        else {
            return "Fail";
        }
    }
}
