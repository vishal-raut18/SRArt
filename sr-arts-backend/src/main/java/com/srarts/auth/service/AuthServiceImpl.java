package com.srarts.auth.service;

import com.srarts.auth.dto.request.LoginRequest;
import com.srarts.auth.dto.request.RegisterRequest;
import com.srarts.auth.dto.response.LoginResponse;
import com.srarts.auth.dto.response.RegisterResponse;
import com.srarts.common.enums.Role;
import com.srarts.common.enums.UserStatus;
import com.srarts.common.exception.DuplicateResourceException;
import com.srarts.security.jwt.JwtService;
import com.srarts.user.entity.User;
import com.srarts.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest request) {

        String email = request.getEmail().trim().toLowerCase();
        String mobileNumber = request.getMobileNumber().trim();

        if (userRepository.existsByEmailIgnoreCase(email)) {
            throw new DuplicateResourceException("Email is already registered");
        }

        if (userRepository.existsByMobileNumber(mobileNumber)) {
            throw new DuplicateResourceException("Mobile number is already registered");
        }

        User user = User.builder()
                .firstName(request.getFirstName().trim())
                .lastName(
                        request.getLastName() == null
                                ? null
                                : request.getLastName().trim()
                )
                .email(email)
                .mobileNumber(mobileNumber)
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .status(UserStatus.ACTIVE)
                .emailVerified(false)
                .mobileVerified(false)
                .build();

        User savedUser = userRepository.save(user);

        return RegisterResponse.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .mobileNumber(savedUser.getMobileNumber())
                .role(savedUser.getRole().name())
                .status(savedUser.getStatus().name())
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getIdentifier(),
                                request.getPassword()
                        )
                );

        String username = authentication.getName();

        String token =
                jwtService.generateToken(username);

        return LoginResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .expiresIn(3600)
                .build();
    }
}