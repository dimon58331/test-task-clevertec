package com.clevertec.test.dao;

import com.clevertec.test.entity.DiscountCardTest;

import java.util.List;

public interface IDiscountDAOTest {
    public List<DiscountCardTest> getAllDiscountCardsFromDataBase();
    public DiscountCardTest getDiscountCardFromDataBaseByID(int id);
    public void saveDiscountCardToDataBase(DiscountCardTest discountCardTest);
    public void deleteDiscountCardFromDataBaseByID(int id);
    public DiscountCardTest getDiscountCardFromDataBaseByCardNumber(int cardNumber);
}
