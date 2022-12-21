package com.clevertec.test.exception;

public class NullReceiptExceptionTest extends RuntimeException{
    private static String message = "ReceiptTest is null!";
    public NullReceiptExceptionTest(){
        super(message);
    }
    public NullReceiptExceptionTest(String message) {
        super(message);
    }
}
