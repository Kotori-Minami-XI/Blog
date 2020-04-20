package com.Kotori.dao.impl;

import com.Kotori.dao.CategoryDao;
import com.Kotori.domain.Category;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.transaction.Transactional;

@Transactional
public class CategoryDaoImpl extends HibernateDaoSupport implements CategoryDao {

    @Override
    public String addCategory(Category category) {
        this.getHibernateTemplate().save(category);
        return null;
    }

}
