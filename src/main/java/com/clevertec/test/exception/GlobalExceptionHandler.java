package com.clevertec.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<String> handleException(InvalidIDException idException){
        return new ResponseEntity<>(idException.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<String> handleException(InvalidProductException productException){
        return new ResponseEntity<>(productException.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<String> handleException(InvalidQuantityException quantityException){
        return new ResponseEntity<>(quantityException.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<String> handleException(NullReceiptException receiptException){
        return new ResponseEntity<>(receiptException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(InvalidDiscountCardException discountCardException){
        return new ResponseEntity<>(discountCardException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
