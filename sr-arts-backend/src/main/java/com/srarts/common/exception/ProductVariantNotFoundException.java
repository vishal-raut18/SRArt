package com.srarts.common.exception;

public class ProductVariantNotFoundException
        extends RuntimeException {

    public ProductVariantNotFoundException(String message) {
        super(message);
    }
}