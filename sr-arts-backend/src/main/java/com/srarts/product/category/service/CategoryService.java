package com.srarts.product.category.service;

import com.srarts.product.category.dto.CategoryResponse;
import com.srarts.product.category.dto.CreateCategoryRequest;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(CreateCategoryRequest request);

    List<CategoryResponse> getAllActiveCategories();

    CategoryResponse getCategoryById(Long id);

    CategoryResponse updateCategory(Long id, CreateCategoryRequest request);

    void deactivateCategory(Long id);
}