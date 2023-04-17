package com.raza.ecommerce.service.impl;

import com.raza.ecommerce.dto.request.LoginRequest;
import com.raza.ecommerce.dto.request.RegisterRequest;
import com.raza.ecommerce.dto.response.LoginResponseDto;
import com.raza.ecommerce.entity.User;
import com.raza.ecommerce.repository.UserRepository;
import com.raza.ecommerce.service.AuthenticationService;
import org.apache.catalina.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void register(RegisterRequest registerRequest) {
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .build();
        userRepository.save(user);
    }

    @Override
    public LoginResponseDto login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return LoginResponseDto.builder()
                .userId(user.getId())
                .token(jwtService.generateToken(user))
                .build();
    }
}
