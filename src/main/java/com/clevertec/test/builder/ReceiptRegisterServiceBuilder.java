package com.clevertec.test.builder;

import com.clevertec.test.service.IReceiptRegisterService;
import org.springframework.stereotype.Component;

public class ReceiptRegisterServiceBuilder {
    private IReceiptRegisterService receiptRegisterService;

    public void setReceiptRegisterService(IReceiptRegisterService receiptRegisterService) {
        this.receiptRegisterService = receiptRegisterService;
    }

    public IReceiptRegisterService getReceiptRegisterServiceWithParameters(){
        return receiptRegisterService;
    }
}
