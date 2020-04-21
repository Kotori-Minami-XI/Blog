package com.Kotori.dao;

import com.Kotori.domain.Article;
import com.Kotori.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface ArticleDao {
    List<Article> getAllArticle();

    List<Article> getPageData(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize);

    Integer getTotalArticleCount(DetachedCriteria detachedCriteria);
}
