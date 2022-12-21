package com.clevertec.test.service;

import com.clevertec.test.entity.DiscountCard;
import com.clevertec.test.entity.Product;
import com.clevertec.test.entity.ProductsList;

import java.util.List;

public interface IProductAndDiscountService {
    public List<Product> getAllProductsFromDataBase();
    public void saveProductToDataBase(Product product);
    public Product getProductFromDataBaseByID(int id);
    public void deleteProductFromDataBaseByID(int id);

    public List<ProductsList> getAllProductsListFromDataBase();

    public List<DiscountCard> getAllDiscountCardsFromDataBase();
    public DiscountCard getDiscountCardFromDataBaseByID(int id);
    public void saveDiscountCardToDataBase(DiscountCard discountCard);
    public void deleteDiscountCardFromDataBaseByCardNumber(int cardNumber);

    public DiscountCard getDiscountCardFromDataBaseByCardNumber(int cardNumber);
}
