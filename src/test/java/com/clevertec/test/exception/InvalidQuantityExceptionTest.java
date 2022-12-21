package com.clevertec.test.exception;

public class InvalidQuantityExceptionTest extends RuntimeException{
    private static String message = "Invalid quantity!";

    public InvalidQuantityExceptionTest(){
        super(message);
    }
    public InvalidQuantityExceptionTest(String message) {
        super(message);
    }
}
