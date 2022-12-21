package com.clevertec.test.dao;

import com.clevertec.test.entity.DiscountCard;

import java.util.List;

public interface IDiscountDAO {
    public List<DiscountCard> getAllDiscountCardsFromDataBase();
    public DiscountCard getDiscountCardFromDataBaseByID(int id);
    public void saveDiscountCardToDataBase(DiscountCard discountCard);
    public void deleteDiscountCardFromDataBaseByID(int id);
    public DiscountCard getDiscountCardFromDataBaseByCardNumber(int cardNumber);
}
