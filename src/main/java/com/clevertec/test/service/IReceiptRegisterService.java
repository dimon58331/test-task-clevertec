package com.clevertec.test.service;

import com.clevertec.test.entity.DiscountCard;

import com.clevertec.test.entity.Receipt;
import com.clevertec.test.utility.SmartFileScanner;

public interface IReceiptRegisterService {

    public String createFullReceipt();

    public void addDiscountCardToReceipt(DiscountCard discountCard);

    public void addProductToReceiptByID(int id, int quantity);

    public void deleteProductFromReceiptByID(int id, int quantity);

    public Receipt getReceipt();

    public void setReceipt(Receipt receipt);
}
