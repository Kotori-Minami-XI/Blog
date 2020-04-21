package com.Kotori.domain;

public class Article {
    private Integer article_id;
    private String article_title;
    private String article_content;
    private String article_date;
    private String article_pic;
    private String article_desc;
    private Integer article_cid;
    private Category category; // Outer Key

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public String getArticle_date() {
        return article_date;
    }

    public void setArticle_date(String article_date) {
        this.article_date = article_date;
    }

    public String getArticle_pic() {
        return article_pic;
    }

    public void setArticle_pic(String article_pic) {
        this.article_pic = article_pic;
    }

    public String getArticle_desc() {
        return article_desc;
    }

    public void setArticle_desc(String article_desc) {
        this.article_desc = article_desc;
    }

    public Integer getArticle_cid() {
        return article_cid;
    }

    public void setArticle_cid(Integer article_cid) {
        this.article_cid = article_cid;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Article{" +
                "article_id=" + article_id +
                ", article_title='" + article_title + '\'' +
                ", article_content='" + article_content + '\'' +
                ", article_date='" + article_date + '\'' +
                ", article_pic='" + article_pic + '\'' +
                ", article_desc='" + article_desc + '\'' +
                ", article_cid=" + article_cid +
                ", category=" + category +
                '}';
    }
}
