package com.srarts.product.material.service;

import com.srarts.product.material.dto.MaterialResponse;

import java.util.List;

public interface ProductMaterialService {

    void assignMaterial(
            Long productId,
            Long materialId
    );

    void removeMaterial(
            Long productId,
            Long materialId
    );

    List<MaterialResponse> getProductMaterials(
            Long productId
    );
}