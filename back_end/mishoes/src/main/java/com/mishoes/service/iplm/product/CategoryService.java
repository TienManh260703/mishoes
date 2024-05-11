package com.mishoes.service.iplm.product;

import com.mishoes.dto.request.create.product.CategoryRequest;
import com.mishoes.exception.DataAlreadyExistsException;
import com.mishoes.exception.DataNotFoundException;
import com.mishoes.exception.ErrorCode;
import com.mishoes.mapper.product.CategoryMapper;
import com.mishoes.entity.Category;
import com.mishoes.repository.CategoryRepository;
import com.mishoes.service.ICategoryService;
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
    public Category createCategory(CategoryRequest dto) {
        if(categoryRepository.existsByCode(dto.getCode())){
            throw  new DataAlreadyExistsException(ErrorCode.CATEGORY_EXISTED.toString());
        }
        if(categoryRepository.existsByName(dto.getName())){
            throw   new DataNotFoundException(ErrorCode.CATEGORY_EXISTED.toString());
        }
        return categoryRepository.save(categoryMapper.toCategory(dto));
    }

    @Override
    public Category updateCategory(String id, CategoryRequest dto) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(
                () -> {
                    throw new DataNotFoundException("Cannot find category with id : " + id);
                });
        if (categoryRepository.existsByName(dto.getName().trim())) {
            throw new DataAlreadyExistsException("Category name already exists");
        }
        categoryMapper.updateCategory(existingCategory, dto);
        return categoryRepository.save(existingCategory);
    }

    @Override
    public String deleteCategory(String id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            categoryRepository.deleteById(id);
            return "Category deleted";
        }
        return "Delete failed category";

    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(String id) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    throw new DataNotFoundException("Cannot find Category with id : " + id);
                });
        return existingCategory;
    }
}
