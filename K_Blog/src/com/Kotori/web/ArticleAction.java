package com.Kotori.web;

import com.Kotori.domain.Article;
import com.Kotori.domain.Category;
import com.Kotori.domain.PageBean;
import com.Kotori.service.ArticleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.IOException;
import java.util.List;

public class ArticleAction extends ActionSupport implements ModelDriven<Article> {
    private ArticleService articleService;
    private Article article = new Article();
    private Integer currentPage = 1;
    private Integer parentId;
    private String keyword = null;

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

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String pageList(){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        // Non-null keyword indicates a vague search by article title
        if (null !=this.keyword) {
            detachedCriteria.add(Restrictions.like("article_title", "%" + this.keyword + "%"));
        }
        PageBean pageBean = articleService.getPageData(detachedCriteria, this.currentPage, 5);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "ARTICLE_SUBPAGE_OBTAIN_SUCCESS";
    }

    public String deleteArticle(){
        articleService.deleteArticle(this.article);
        return "ARTICLE_DELETE_SUCCESS";
    }

    public String getCategory() throws IOException {
        List<Category> list = articleService.getCategoryByParentId(this.parentId);

        // Convert to json so that the front page retrieves items from json
        JSONArray jsonArray = JSONArray.fromObject(list, new JsonConfig());
        ServletActionContext.getResponse().setContentType("text/html:charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return null;
    }
}
