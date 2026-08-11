package com.srarts.product.service.impl;

import com.srarts.common.enums.ProductStatus;
import com.srarts.common.exception.CategoryNotFoundException;
import com.srarts.common.exception.DuplicateProductException;
import com.srarts.common.exception.ProductNotFoundException;
import com.srarts.product.category.entity.Category;
import com.srarts.product.category.repository.CategoryRepository;
import com.srarts.product.dto.CreateProductRequest;
import com.srarts.product.dto.ProductResponse;
import com.srarts.product.dto.UpdateProductRequest;
import com.srarts.product.entity.Product;
import com.srarts.product.repository.ProductRepository;
import com.srarts.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponse createProduct(CreateProductRequest request) {

        if (productRepository.existsByNameIgnoreCase(request.name())) {
            throw new DuplicateProductException(
                    "Product already exists: " + request.name()
            );
        }

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id: " + request.categoryId()
                        )
                );

        Product product = Product.builder()
                .category(category)
                .name(request.name().trim())
                .description(request.description())
                .basePrice(request.basePrice())
                .status(ProductStatus.DRAFT)
                .build();

        Product savedProduct = productRepository.save(product);

        return mapToResponse(savedProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getActiveProducts() {

        return productRepository
                .findByStatus(ProductStatus.ACTIVE)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + id
                        )
                );

        return mapToResponse(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getProductsByCategory(Long categoryId) {

        return productRepository
                .findByCategoryIdAndStatus(
                        categoryId,
                        ProductStatus.ACTIVE
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProductResponse updateProduct(
            Long id,
            UpdateProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + id
                        )
                );

        if (!product.getName().equalsIgnoreCase(request.name())
                && productRepository.existsByNameIgnoreCase(request.name())) {

            throw new DuplicateProductException(
                    "Product already exists: " + request.name()
            );
        }

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id: " + request.categoryId()
                        )
                );

        product.setCategory(category);
        product.setName(request.name().trim());
        product.setDescription(request.description());
        product.setBasePrice(request.basePrice());

        return mapToResponse(product);
    }


    @Override
    public ProductResponse updateProductStatus(
            Long id,
            ProductStatus status) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + id
                        )
                );

        product.setStatus(status);

        return mapToResponse(product);
    }
    private ProductResponse mapToResponse(Product product) {

        return new ProductResponse(
                product.getId(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getName(),
                product.getDescription(),
                product.getBasePrice(),
                product.getStatus()
        );
    }
}