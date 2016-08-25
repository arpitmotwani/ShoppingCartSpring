package com.bitwise.onlineshoppingcart.model;

import org.springframework.stereotype.Component;

/**
 * Created by arpitm on 8/22/2016.
 */
public class ProductNotAvailableException extends RuntimeException {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ProductNotAvailableException(String message) {
            this.message=message;
    }
}
