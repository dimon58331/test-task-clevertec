package com.clevertec.test.exception;

public class InvalidIDException extends RuntimeException{
    private static String message = "Invalid ID!";

    public InvalidIDException(){
        super(message);
    }
    public InvalidIDException(String message) {
        super(message);
    }
}
