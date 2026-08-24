package com.srarts.product.font.service;

import com.srarts.product.font.dto.*;

import java.util.List;

public interface FontService {

    FontResponse create(CreateFontRequest request);

    FontResponse getById(Long id);

    List<FontResponse> getActiveFonts();

    FontResponse update(Long id, UpdateFontRequest request);

    FontResponse updateStatus(Long id, String status);
}