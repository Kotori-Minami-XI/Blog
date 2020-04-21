package com.Kotori.dao.impl;

import com.Kotori.dao.ArticleDao;
import com.Kotori.domain.Article;
import com.Kotori.domain.Category;
import com.Kotori.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ArticleDaoImpl extends HibernateDaoSupport implements ArticleDao {
    @Override
    public List<Article> getAllArticle() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        return (List<Article>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }

    @Override
    public Integer getTotalArticleCount(DetachedCriteria detachedCriteria) {
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list.size() > 0 ? list.get(0).intValue() : 0;
    }

    @Override
    public List<Article> getPageData(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize) {
        detachedCriteria.setProjection(null);
        List<Article> list =
                (List<Article>) this.getHibernateTemplate().findByCriteria(detachedCriteria, currentPage, pageSize);
        return list;
    }

    @Override
    public String deleteArticle(Article article) {
        this.getHibernateTemplate().delete(article);
        return null;
    }

    @Override
    public List<Category> getCategoryByParentId(Integer parentId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Category.class);
        detachedCriteria.add(Restrictions.eq("parentid", parentId.toString()));
        List<Category> list = (List<Category>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list.size() > 0 ? list : null;
    }
}
