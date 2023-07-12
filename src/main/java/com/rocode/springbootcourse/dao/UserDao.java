package com.rocode.springbootcourse.dao;

import com.rocode.springbootcourse.models.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void delete(Long id);

    User getUser(Long id);

    void postUser(User user);

    User getUserByCredentials(User user);
}
