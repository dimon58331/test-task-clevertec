package com.clevertec.test.exception;

import org.springframework.stereotype.Component;

@Component
public class ResponseExceptionHandlerTest {
    private String info;

    public ResponseExceptionHandlerTest() {
    }

    public ResponseExceptionHandlerTest(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
