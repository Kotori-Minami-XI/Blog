package com.Kotori.service.impl;

import com.Kotori.dao.UserDao;
import com.Kotori.domain.User;
import com.Kotori.service.UserService;
import org.hibernate.criterion.DetachedCriteria;

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

    @Override
    public String deleteUser(User user) {
        this.userDao.deleteUser(user);
        return null;
    }

    @Override
    public String addUser(User user) {
        this.userDao.addUser(user);
        return null;
    }

    @Override
    public User getUser(DetachedCriteria detachedCriteria) {
        return this.userDao.getUser(detachedCriteria);
    }

    @Override
    public String updateUser(User user) {
        this.userDao.updateUser(user);
        return null;
    }
}
