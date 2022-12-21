package com.clevertec.test.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ResponseExceptionHandler responseExceptionHandler;

    @ExceptionHandler
    public ResponseEntity<ResponseExceptionHandler> handleException(InvalidIDException idException){
        responseExceptionHandler.setInfo(idException.getMessage());
        return new ResponseEntity<>(responseExceptionHandler, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ResponseExceptionHandler> handleException(InvalidProductException productException){
        responseExceptionHandler.setInfo(productException.getMessage());
        return new ResponseEntity<>(responseExceptionHandler, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ResponseExceptionHandler> handleException(InvalidQuantityException quantityException){
        responseExceptionHandler.setInfo(quantityException.getMessage());
        return new ResponseEntity<>(responseExceptionHandler, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ResponseExceptionHandler> handleException(NullReceiptException receiptException){
        responseExceptionHandler.setInfo(receiptException.getMessage());
        return new ResponseEntity<>(responseExceptionHandler, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseExceptionHandler> handleException
            (InvalidDiscountCardException discountCardException){
        responseExceptionHandler.setInfo(discountCardException.getMessage());
        return new ResponseEntity<>(responseExceptionHandler, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseExceptionHandler> handleException(Exception exception){
        responseExceptionHandler.setInfo(exception.getMessage());
        return new ResponseEntity<>(responseExceptionHandler, HttpStatus.NOT_FOUND);
    }
}
