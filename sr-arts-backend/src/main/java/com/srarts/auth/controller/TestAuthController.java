//package com.srarts.security.controller;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1")
//public class TestAuthController {
//
//    @GetMapping("/test-auth")
//    public String testAuth(Authentication authentication) {
//       // System.out.println(System.getenv("JWT_SECRET"));
//
//        return "Authenticated user: " + authentication.getName();
//    }
//}