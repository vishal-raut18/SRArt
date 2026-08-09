//package com.srarts.security.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class PasswordConfig {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}import io.jsonwebtoken.security.Keys;
//
//import java.util.Base64;
//
//public class KeyGenerator {
//
//    public static void main(String[] args) {
//
//        byte[] keyBytes = Keys.secretKeyFor(
//                io.jsonwebtoken.SignatureAlgorithm.HS256
//        ).getEncoded();
//
//        String secret = Base64.getEncoder()
//                .encodeToString(keyBytes);
//
//        System.out.println(secret);
//    }
//}