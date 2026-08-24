package com.srarts.product.font.service;

import com.srarts.product.font.dto.FontResponse;

import java.util.List;

public interface ProductFontService {

    void assignFont(Long productId, Long fontId);

    void removeFont(Long productId, Long fontId);

    List<FontResponse> getProductFonts(Long productId);
}