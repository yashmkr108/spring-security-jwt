package com.yash.security_labs_jwt.auth.controller;

import com.yash.security_labs_jwt.auth.dto.LoginRequest;
import com.yash.security_labs_jwt.auth.dto.LoginResponse;
import com.yash.security_labs_jwt.auth.dto.RegisterRequest;
import com.yash.security_labs_jwt.auth.dto.RegisterResponse;
import com.yash.security_labs_jwt.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse response = service.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        LoginResponse response = service.login(request);

        return ResponseEntity.ok(response);
    }


}
