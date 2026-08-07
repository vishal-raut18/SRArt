package com.srarts.auth.service;

import com.srarts.auth.dto.request.RegisterRequest;
import com.srarts.auth.dto.response.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);
}