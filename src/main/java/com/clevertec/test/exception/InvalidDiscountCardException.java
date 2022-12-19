package com.clevertec.test.exception;

public class InvalidDiscountCardException extends RuntimeException{
    private static String message = "The discount card does not work!";

    public InvalidDiscountCardException() {
        super(message);
    }

    public InvalidDiscountCardException(String message) {
        super(message);
    }
}
