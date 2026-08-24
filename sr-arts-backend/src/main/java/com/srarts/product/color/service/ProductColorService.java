package com.srarts.product.color.service;

import com.srarts.product.color.dto.ColorResponse;

import java.util.List;

public interface ProductColorService {

    void assignColor(Long productId, Long colorId);

    void removeColor(Long productId, Long colorId);

    List<ColorResponse> getProductColors(Long productId);
}