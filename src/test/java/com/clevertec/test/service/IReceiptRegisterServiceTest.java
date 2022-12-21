package com.clevertec.test.service;

import com.clevertec.test.entity.DiscountCardTest;
import com.clevertec.test.entity.ReceiptTest;

public interface IReceiptRegisterServiceTest {

    public String createFullReceipt();

    public void addDiscountCardToReceipt(DiscountCardTest discountCardTest);

    public void addProductToReceiptByID(int id, int quantity);

    public void deleteProductFromReceiptByID(int id, int quantity);

    public ReceiptTest getReceipt();

    public void setReceipt(ReceiptTest receiptTest);
}
