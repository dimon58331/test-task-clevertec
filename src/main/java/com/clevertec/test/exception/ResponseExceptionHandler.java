package com.clevertec.test.exception;

import org.springframework.stereotype.Component;

@Component
public class ResponseExceptionHandler {
    private String info;

    public ResponseExceptionHandler() {
    }

    public ResponseExceptionHandler(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
