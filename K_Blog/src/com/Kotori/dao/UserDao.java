package com.Kotori.dao;

import com.Kotori.domain.User;

public interface UserDao {
    User getUser(String username, String password);
}
