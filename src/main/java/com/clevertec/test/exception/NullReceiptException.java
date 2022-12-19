package com.clevertec.test.exception;

public class NullReceiptException extends RuntimeException{
    private static String message = "Receipt is null!";
    public NullReceiptException(){
        super(message);
    }
    public NullReceiptException(String message) {
        super(message);
    }
}
