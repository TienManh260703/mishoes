package com.mishoes.services;

import com.mishoes.dtos.CategoryDTO;
import com.mishoes.models.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(CategoryDTO dto);
    Category updateCategory(String id, CategoryDTO dto);
    String deleteCategory (String id);
    List<Category> getCategories();
    Category getCategory(String id);
}
