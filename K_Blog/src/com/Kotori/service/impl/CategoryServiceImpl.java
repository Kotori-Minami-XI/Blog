package com.Kotori.service.impl;

import com.Kotori.dao.CategoryDao;
import com.Kotori.domain.Category;
import com.Kotori.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao;
    @Override
    public String addCategory(Category category) {
        return categoryDao.addCategory(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryDao.getAllCategory();
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
}
