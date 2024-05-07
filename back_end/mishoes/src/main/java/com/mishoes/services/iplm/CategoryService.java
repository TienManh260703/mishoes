package com.mishoes.services.iplm;

import com.mishoes.dtos.CategoryDTO;
import com.mishoes.mappers.CategoryMapper;
import com.mishoes.models.Category;
import com.mishoes.repositories.CategoryRepository;
import com.mishoes.services.ICategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService implements ICategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    @Override
    public Category createCategory(CategoryDTO dto) {
        return null;
    }

    @Override
    public Category updateCategory(String id, CategoryDTO dto) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Cannot find category with id : " + id);
                });
        if (categoryRepository.existsByName(dto.getName().trim())) {
            throw new RuntimeException("Category name already exists");
        }
        categoryMapper.updateCategory(existingCategory, dto);
        return categoryRepository.save(existingCategory);
    }

    @Override
    public String deleteCategory(String id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            throw new RuntimeException("Cannot find category with id : " + id);
        }
        categoryRepository.deleteById(id);
        return "Category deleted";
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(String id) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Cannot find Category with id : " + id);
                });
        return existingCategory;
    }
}
