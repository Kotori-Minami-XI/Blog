package com.Kotori.dao;

import com.Kotori.domain.User;

import java.util.List;

public interface UserDao {
    User getUser(String username, String password);

    List<User> getAllUser();
}
