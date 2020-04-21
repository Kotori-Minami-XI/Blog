package com.Kotori.service.impl;

import com.Kotori.dao.ArticleDao;
import com.Kotori.domain.Article;
import com.Kotori.domain.Category;
import com.Kotori.domain.PageBean;
import com.Kotori.service.ArticleService;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    private ArticleDao articleDao;

    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public List<Article> getAllArticle() {
        return articleDao.getAllArticle();
    }

    @Override
    public PageBean getPageData(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize) {
        PageBean<Article> pageBean = new PageBean<Article>();

        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        // Obtain the total number of records in database
        Integer totalCount = articleDao.getTotalArticleCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        // Compute the total number of pages based on totalCount
        pageBean.setTotalPage(pageBean.getTotalPage());

        // Query results form database
        List<Article> list = articleDao.getPageData(detachedCriteria, pageBean.getIndex(), pageBean.getPageSize());
        pageBean.setList(list);

        return pageBean;
    }

    @Override
    public String deleteArticle(Article article) {
        articleDao.deleteArticle(article);
        return null;
    }

    @Override
    public List<Category> getCategoryByParentId(Integer parentId) {
        return articleDao.getCategoryByParentId(parentId);
    }
}
