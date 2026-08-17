package com.srarts.common.exception;

public class ProductImageLimitException
        extends RuntimeException {

    public ProductImageLimitException(String message) {
        super(message);
    }
}