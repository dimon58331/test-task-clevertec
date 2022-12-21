package com.clevertec.test.service;

import com.clevertec.test.entity.DiscountCardTest;
import com.clevertec.test.entity.ProductTest;
import com.clevertec.test.entity.ProductsListTest;

import java.util.List;

public interface IProductAndDiscountServiceTest {
    public List<ProductTest> getAllProductsFromDataBase();
    public void saveProductToDataBase(ProductTest productTest);
    public ProductTest getProductFromDataBaseByID(int id);
    public void deleteProductFromDataBaseByID(int id);

    public List<ProductsListTest> getAllProductsListFromDataBase();

    public List<DiscountCardTest> getAllDiscountCardsFromDataBase();
    public DiscountCardTest getDiscountCardFromDataBaseByID(int id);
    public void saveDiscountCardToDataBase(DiscountCardTest discountCardTest);
    public void deleteDiscountCardFromDataBaseByCardNumber(int cardNumber);

    public DiscountCardTest getDiscountCardFromDataBaseByCardNumber(int cardNumber);
}
