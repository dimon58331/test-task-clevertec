package com.clevertec.test.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerTest {

    @Autowired
    private ResponseExceptionHandlerTest responseExceptionHandlerTest;

    @ExceptionHandler
    public ResponseEntity<ResponseExceptionHandlerTest> handleException(InvalidIDExceptionTest idException){
        responseExceptionHandlerTest.setInfo(idException.getMessage());
        return new ResponseEntity<>(responseExceptionHandlerTest, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ResponseExceptionHandlerTest> handleException(InvalidProductExceptionTest productException){
        responseExceptionHandlerTest.setInfo(productException.getMessage());
        return new ResponseEntity<>(responseExceptionHandlerTest, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ResponseExceptionHandlerTest> handleException(InvalidQuantityExceptionTest quantityException){
        responseExceptionHandlerTest.setInfo(quantityException.getMessage());
        return new ResponseEntity<>(responseExceptionHandlerTest, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ResponseExceptionHandlerTest> handleException(NullReceiptExceptionTest receiptException){
        responseExceptionHandlerTest.setInfo(receiptException.getMessage());
        return new ResponseEntity<>(responseExceptionHandlerTest, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseExceptionHandlerTest> handleException
            (InvalidDiscountCardExceptionTest discountCardException){
        responseExceptionHandlerTest.setInfo(discountCardException.getMessage());
        return new ResponseEntity<>(responseExceptionHandlerTest, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseExceptionHandlerTest> handleException(Exception exception){
        responseExceptionHandlerTest.setInfo(exception.getMessage());
        return new ResponseEntity<>(responseExceptionHandlerTest, HttpStatus.NOT_FOUND);
    }
}
