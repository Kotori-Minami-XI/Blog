package com.Kotori.service.impl;

import com.Kotori.dao.UserDao;
import com.Kotori.domain.User;
import com.Kotori.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }
}
