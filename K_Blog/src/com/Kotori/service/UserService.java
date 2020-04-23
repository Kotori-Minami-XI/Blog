package com.Kotori.service;

import com.Kotori.domain.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    String deleteUser(User user);
}
