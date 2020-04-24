package com.Kotori.web;

import com.Kotori.domain.Article;
import com.Kotori.domain.PageBean;
import com.Kotori.service.ArticleService;

import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;


public class WebAction extends ActionSupport {
    private ArticleService articleService;
    private Integer currentPage = 1;

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    public String getPageList() throws IOException {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        PageBean<Article> pageBean = this.articleService.getPageData(detachedCriteria, currentPage, 5);

        // Initialize Lazy mechanism because Category is a reference in Article
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"handler", "hibernateLazyInitializer"});
        JSONObject jsonObj = JSONObject.fromObject(pageBean, jsonConfig);

        ServletActionContext.getResponse().setContentType("text/html:charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonObj.toString());
        return null;
    }
}
