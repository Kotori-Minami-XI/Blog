package com.Kotori.web;

import com.Kotori.domain.Category;
import com.Kotori.service.CategoryService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

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

    public String addCategory(){
        String serviceCode = categoryService.addCategory(this.category);
        return null;
    }
}
