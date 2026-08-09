package com.srarts;

import io.jsonwebtoken.security.Keys;

import java.util.Base64;

public class KeyGenerator {
    public static void main(String[] args) {
        byte[] keyBytes = Keys.secretKeyFor(
                io.jsonwebtoken.SignatureAlgorithm.HS256
        ).getEncoded();

        String secret = Base64.getEncoder()
                .encodeToString(keyBytes);

        System.out.println(secret);
    }
}
