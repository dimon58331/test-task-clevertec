package com.clevertec.test.builder;

import com.clevertec.test.service.IReceiptRegisterServiceTest;

public class ReceiptRegisterServiceBuilderTest {
    private IReceiptRegisterServiceTest receiptRegisterService;

    public void setReceiptRegisterService(IReceiptRegisterServiceTest receiptRegisterService) {
        this.receiptRegisterService = receiptRegisterService;
    }

    public IReceiptRegisterServiceTest getReceiptRegisterServiceWithParameters(){
        return receiptRegisterService;
    }
}
