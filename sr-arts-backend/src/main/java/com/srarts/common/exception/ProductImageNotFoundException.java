package com.srarts.common.exception;

public class ProductImageNotFoundException
        extends RuntimeException {

    public ProductImageNotFoundException(String message) {
        super(message);
    }
}