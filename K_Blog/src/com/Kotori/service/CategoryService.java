package com.Kotori.service;

import com.Kotori.domain.Category;

import java.util.List;

public interface CategoryService {
    String addCategory(Category category);

    List<Category> getAllCategory();

    Category queryCategoryByCid(Integer cid);

    String updateCategory(Category category);

    String deleteCategory(Category category);
}
