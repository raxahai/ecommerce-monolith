package com.raza.ecommerce.service;

import com.raza.ecommerce.dto.request.LoginRequest;
import com.raza.ecommerce.dto.request.RegisterRequest;
import com.raza.ecommerce.dto.response.LoginResponseDto;

public interface AuthenticationService {
    void register(RegisterRequest registerRequest);
    LoginResponseDto login(LoginRequest loginRequest);
}
