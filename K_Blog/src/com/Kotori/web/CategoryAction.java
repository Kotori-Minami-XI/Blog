package com.Kotori.web;

import com.Kotori.domain.Category;
import com.Kotori.service.CategoryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

public class CategoryAction extends ActionSupport implements ModelDriven<Category> {
    private Category category = new Category();
    private CategoryService categoryService;

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Category getModel() {
        return category;
    }

    /***
     * @Brief  Add category to database
     * @return null;
     */
    public String addCategory(){
        categoryService.addCategory(this.category);
        return null;
    }

    /***
     * @Brief Obtain all categories and dispaly them in the front page
     * @return null
     */
    public String listCategory(){
        List<Category> list = categoryService.getAllCategory();
        // Store results in value stack
        ActionContext.getContext().getValueStack().set("categoryList", list);
        return "CATEGORY_ALL_OBTAIN_SUCCESS";
    }
}
