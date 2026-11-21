package com.yash.security_labs_jwt.auth.service;

import com.yash.security_labs_jwt.auth.dto.LoginRequest;
import com.yash.security_labs_jwt.auth.dto.LoginResponse;
import com.yash.security_labs_jwt.auth.dto.RegisterRequest;
import com.yash.security_labs_jwt.auth.dto.RegisterResponse;
import com.yash.security_labs_jwt.security.jwt.JwtService;
import com.yash.security_labs_jwt.security.userdetails.CustomUserDetails;
import com.yash.security_labs_jwt.user.entity.User;
import com.yash.security_labs_jwt.user.enums.UserRole;
import com.yash.security_labs_jwt.user.enums.UserStatus;
import com.yash.security_labs_jwt.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already registered");
        }
        String encodedPassword = passwordEncoder.encode(request.password());

        User user = new User(request.email(), encodedPassword, UserRole.USER, UserStatus.ACTIVE);

        User savedUser = userRepository.save(user);

        return new RegisterResponse(savedUser.getId(), savedUser.getEmail());
    }

    public LoginResponse login(LoginRequest request) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.email(),
                                request.password()
                        )
                );

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(
                userDetails.getUsername(),
                userDetails.getUserId()
        );
        return new LoginResponse(token, "Bearer");
    }
}
