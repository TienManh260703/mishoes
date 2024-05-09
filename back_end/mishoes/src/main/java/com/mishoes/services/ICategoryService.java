package com.mishoes.services;

import com.mishoes.dtos.requests.create.product.CategoryRequest;
import com.mishoes.entity.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(CategoryRequest dto);
    Category updateCategory(String id, CategoryRequest dto);
    String deleteCategory (String id);
    List<Category> getCategories();
    Category getCategory(String id);
}
