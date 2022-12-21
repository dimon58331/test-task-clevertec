package com.clevertec.test.exception;

public class InvalidIDExceptionTest extends RuntimeException{
    private static String message = "Invalid ID!";

    public InvalidIDExceptionTest(){
        super(message);
    }
    public InvalidIDExceptionTest(String message) {
        super(message);
    }
}
