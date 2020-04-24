package com.Kotori.service;

import com.Kotori.domain.User;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    String deleteUser(User user);

    String addUser(User user);

    User getUser(DetachedCriteria detachedCriteria);

    String updateUser(User user);
}
