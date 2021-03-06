package com.Kotori.service.impl;

import com.Kotori.dao.CategoryDao;
import com.Kotori.domain.Category;
import com.Kotori.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public String addCategory(Category category) {
        return categoryDao.addCategory(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryDao.getAllCategory();
    }

    @Override
    public Category queryCategoryByCid(Integer cid) {
        return categoryDao.queryCategoryByCid(cid);
    }

    @Override
    public String updateCategory(Category category) {
        categoryDao.updateCategory(category);
        return null;
    }

    @Override
    public String deleteCategory(Category category) {
        categoryDao.deleteCategory(category);
        return null;
    }
}
