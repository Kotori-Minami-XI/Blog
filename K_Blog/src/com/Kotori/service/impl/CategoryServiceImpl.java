package com.Kotori.service.impl;

import com.Kotori.dao.CategoryDao;
import com.Kotori.domain.Category;
import com.Kotori.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao;
    @Override
    public String addCategory(Category category) {
        return categoryDao.addCategory(category);
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
}
