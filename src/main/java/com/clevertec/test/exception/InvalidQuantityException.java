package com.clevertec.test.exception;

public class InvalidQuantityException extends RuntimeException{
    private static String message = "Invalid quantity!";

    public InvalidQuantityException(){
        super(message);
    }
    public InvalidQuantityException(String message) {
        super(message);
    }
}
