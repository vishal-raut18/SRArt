package com.srarts.product.category.service.impl;

import com.srarts.common.exception.CategoryNotFoundException;
import com.srarts.common.exception.DuplicateCategoryException;
import com.srarts.product.category.dto.CategoryResponse;
import com.srarts.product.category.dto.CreateCategoryRequest;
import com.srarts.product.category.entity.Category;
import com.srarts.product.category.repository.CategoryRepository;
import com.srarts.product.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse createCategory(CreateCategoryRequest request) {

        if (categoryRepository.existsByNameIgnoreCase(request.name())) {
            throw new DuplicateCategoryException(
                    "Category already exists: " + request.name()
            );
        }

        Category category = Category.builder()
                .name(request.name().trim())
                .description(request.description())
                .active(true)
                .build();

        Category savedCategory = categoryRepository.save(category);

        return mapToResponse(savedCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllActiveCategories() {

        return categoryRepository.findAll()
                .stream()
                .filter(Category::getActive)
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse getCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id: " + id
                        )
                );

        return mapToResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(
            Long id,
            CreateCategoryRequest request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id: " + id
                        )
                );

        if (!category.getName().equalsIgnoreCase(request.name())
                && categoryRepository.existsByNameIgnoreCase(request.name())) {

            throw new DuplicateCategoryException(
                    "Category already exists: " + request.name()
            );
        }

        category.setName(request.name().trim());
        category.setDescription(request.description());

        return mapToResponse(category);
    }

    @Override
    public void deactivateCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id: " + id
                        )
                );

        category.setActive(false);
    }

    private CategoryResponse mapToResponse(Category category) {

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getActive()
        );
    }
}