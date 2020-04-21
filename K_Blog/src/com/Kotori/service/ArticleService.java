package com.Kotori.service;

import com.Kotori.domain.Article;
import com.Kotori.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface ArticleService {
    List<Article> getAllArticle();

    PageBean getPageData(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize);
}
