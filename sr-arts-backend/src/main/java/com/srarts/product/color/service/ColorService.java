package com.srarts.product.color.service;

import com.srarts.product.color.dto.*;

import java.util.List;

public interface ColorService {

    ColorResponse create(CreateColorRequest request);

    ColorResponse getById(Long id);

    List<ColorResponse> getActiveColors();

    ColorResponse update(Long id, UpdateColorRequest request);

    ColorResponse updateStatus(Long id, String status);
}