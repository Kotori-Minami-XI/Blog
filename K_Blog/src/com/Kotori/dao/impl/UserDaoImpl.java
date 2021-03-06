package com.Kotori.dao.impl;

import com.Kotori.dao.UserDao;
import com.Kotori.domain.User;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
    @Override
    public User getUser(String username, String password) {
        // QBC query
        // Step 1: Establish criteria
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        // Step 2: Add criteria
        detachedCriteria.add(Restrictions.eq("username", username));
        detachedCriteria.add(Restrictions.eq("password", password));
        // Step 3: Execute
        List<User> list = (List<User>)this.getHibernateTemplate().findByCriteria(detachedCriteria);

        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<User> getAllUser() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        List<User> list = (List<User>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        System.out.println(list);
        return list.size() > 0 ? list : null;
    }

    @Override
    public String deleteUser(User user) {
        this.getHibernateTemplate().delete(user);
        return null;
    }

    @Override
    public String addUser(User user) {
        this.getHibernateTemplate().save(user);
        return null;
    }

    @Override
    public User getUser(DetachedCriteria detachedCriteria) {
        List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public String updateUser(User user) {
        // Reminder: User clear() to clean session before updating
        this.getHibernateTemplate().clear();
        this.getHibernateTemplate().update(user);
        return null;
    }
}
