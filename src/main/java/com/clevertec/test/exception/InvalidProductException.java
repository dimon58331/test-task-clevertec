package com.clevertec.test.exception;

public class InvalidProductException extends RuntimeException{
    private static String message = "The product does not exist!";

    public InvalidProductException(){
        super(message);
    }
    public InvalidProductException(String message) {

        super(message);
    }
}
