package com.Kotori.service.impl;

import com.Kotori.dao.UserDao;
import com.Kotori.dao.impl.UserDaoImpl;
import com.Kotori.domain.User;
import com.Kotori.service.LoginService;

public class LoginServiceImpl implements LoginService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(User user) {
        return userDao.getUser(user.getUsername(),user.getPassword());
    }

}
