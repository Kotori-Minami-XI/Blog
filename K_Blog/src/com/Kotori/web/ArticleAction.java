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
import org.aspectj.util.FileUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ArticleAction extends ActionSupport implements ModelDriven<Article> {
    private ArticleService articleService;
    private Article article = new Article();
    private Integer currentPage = 1;
    private Integer parentId;
    private String keyword = null;

    // Upload files
    private String uploadFileName;
    private File upload;
    private String uploadContentType;

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public Article getModel() {
        return this.article;
    }

    public String listArticle(){
        List<Article> list = this.articleService.getAllArticle();
        ActionContext.getContext().getValueStack().set("allArticle", list);
        return "ARTICLE_ALL_OBTAIN_SUCCESS";
    }

    public String pageList(){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        // Non-null keyword indicates a vague search by article title
        if (null !=this.keyword) {
            detachedCriteria.add(Restrictions.like("article_title", "%" + this.keyword + "%"));
        }
        PageBean pageBean = this.articleService.getPageData(detachedCriteria, this.currentPage, 5);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "ARTICLE_SUBPAGE_OBTAIN_SUCCESS";
    }

    public String deleteArticle(){
        this.articleService.deleteArticle(this.article);
        return "ARTICLE_DELETE_SUCCESS";
    }

    public String getCategory() throws IOException {
        List<Category> list = this.articleService.getCategoryByParentId(this.parentId);

        // Convert to json so that the front page retrieves items from json
        JSONArray jsonArray = JSONArray.fromObject(list, new JsonConfig());
        ServletActionContext.getResponse().setContentType("text/html:charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return null;
    }

    public String addArticle() throws IOException {
        String pictureName = this.handleUploadedFile();
        this.article.setArticle_pic(pictureName);
        Long date = new Date().getTime();
        this.article.setArticle_date(date.toString());

        articleService.addArticle(this.article);
        return "ARTICLE_ADD_SUCCESS";
    }

    public String editArticle() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        detachedCriteria.add(Restrictions.eq("article_id", this.article.getArticle_id()));
        List<Article> list = articleService.getArticle(detachedCriteria);

        ActionContext.getContext().getValueStack().push(list.get(0));
        return "ARTICLE_OBTAIN_SUCCESS";
    }

    public String updateArticle() throws IOException {
        String pictureName = this.handleUploadedFile();
        this.article.setArticle_pic(pictureName);
        Long date = new Date().getTime();
        this.article.setArticle_date(date.toString());

        this.articleService.updateArticle(this.article);
        return null;
    }

    private String handleUploadedFile() throws IOException {
        if (null != this.upload){
            // Step 1 : Randomly generate file names
            String suffix = this.uploadFileName.substring(this.uploadFileName.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString().replace("-","");
            String fileName = uuid + suffix;

            // Step 2 : Locate and generate upload file
            String uploadDirPath = ServletActionContext.getServletContext().getRealPath("/uploadDir");
            File file = new File(uploadDirPath);
            if (!file.exists()){
                file.mkdirs();
            }
            File finalFile = new File(uploadDirPath + "/" + fileName);

            // Step 3 : Upload Files (Copy from old file to new file)
            FileUtil.copyFile(this.upload, finalFile);

            return fileName;
        }
        return null;
    }
}
