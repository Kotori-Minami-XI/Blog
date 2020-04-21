package com.Kotori.web;

import com.Kotori.domain.Article;
import com.Kotori.domain.PageBean;
import com.Kotori.service.ArticleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class ArticleAction extends ActionSupport implements ModelDriven<Article> {
    private ArticleService articleService;
    private Article article = new Article();
    private Integer currentPage = 1;

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public Article getModel() {
        return this.article;
    }

    public String listArticle(){
        List<Article> list = articleService.getAllArticle();
        ActionContext.getContext().getValueStack().set("allArticle", list);
        return "ARTICLE_ALL_OBTAIN_SUCCESS";
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String pageList(){
        System.out.println(currentPage);

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        PageBean pageBean = articleService.getPageData(detachedCriteria, this.currentPage, 5);
        System.out.println(pageBean);
        return null;
    }
}
