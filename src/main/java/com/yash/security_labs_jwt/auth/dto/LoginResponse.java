package com.yash.security_labs_jwt.auth.dto;

public record LoginResponse(
        String accessToken,
        String tokenType
) {}