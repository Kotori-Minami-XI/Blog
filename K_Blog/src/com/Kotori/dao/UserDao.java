package com.Kotori.dao;

import com.Kotori.domain.User;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface UserDao {
    User getUser(String username, String password);

    List<User> getAllUser();

    String deleteUser(User user);

    String addUser(User user);

    User getUser(DetachedCriteria detachedCriteria);

    String updateUser(User user);
}
