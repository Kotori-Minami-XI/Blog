package com.Kotori.dao.impl;

import com.Kotori.dao.CategoryDao;
import com.Kotori.domain.Category;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class CategoryDaoImpl extends HibernateDaoSupport implements CategoryDao {

    @Override
    public String addCategory(Category category) {
        this.getHibernateTemplate().save(category);
        return null;
    }

    @Override
    public List<Category> getAllCategory() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Category.class);
        return (List<Category>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }

    @Override
    public Category queryCategoryByCid(Integer cid) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Category.class);
        detachedCriteria.add(Restrictions.eq("cid", cid));
        List<Category> list = (List<Category>) this.getHibernateTemplate().findByCriteria(detachedCriteria);

        return list.size() > 0 ? list.get(0) : null;
    }
}
