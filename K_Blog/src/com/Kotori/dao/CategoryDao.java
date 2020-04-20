package com.Kotori.dao;

import com.Kotori.domain.Category;

import java.util.List;

public interface CategoryDao {
    String addCategory(Category category);

    List<Category> getAllCategory();

    Category queryCategoryByCid(Integer cid);
}
