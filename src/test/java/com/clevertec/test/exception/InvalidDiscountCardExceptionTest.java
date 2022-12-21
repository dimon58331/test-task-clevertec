package com.clevertec.test.exception;

public class InvalidDiscountCardExceptionTest extends RuntimeException{
    private static String message = "The discount card does not work!";

    public InvalidDiscountCardExceptionTest() {
        super(message);
    }

    public InvalidDiscountCardExceptionTest(String message) {
        super(message);
    }
}
