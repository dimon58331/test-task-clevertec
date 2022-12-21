package com.clevertec.test.exception;

public class InvalidProductExceptionTest extends RuntimeException{
    private static String message = "The product does not exist!";

    public InvalidProductExceptionTest(){
        super(message);
    }
    public InvalidProductExceptionTest(String message) {

        super(message);
    }
}
