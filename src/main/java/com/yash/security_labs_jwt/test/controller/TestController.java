package com.yash.security_labs_jwt.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test/public")
    public String publicRoute() {
        return "Anyone can access this route";
    }

    @GetMapping("/api/test/protected")
    public String protectedRoute() {
        return "You are authenticated!";
    }
}