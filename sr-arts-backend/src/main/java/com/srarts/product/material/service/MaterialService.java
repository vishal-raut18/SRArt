package com.srarts.product.material.service;

import com.srarts.product.material.dto.CreateMaterialRequest;
import com.srarts.product.material.dto.MaterialResponse;
import com.srarts.product.material.dto.UpdateMaterialRequest;

import java.util.List;

public interface MaterialService {

    MaterialResponse create(CreateMaterialRequest request);

    MaterialResponse getById(Long id);

    List<MaterialResponse> getActiveMaterials();

    MaterialResponse update(Long id, UpdateMaterialRequest request);

    MaterialResponse updateStatus(Long id, String status);
}