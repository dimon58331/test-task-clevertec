package com.clevertec.test.dao;

import com.clevertec.test.entity.ProductTest;
import com.clevertec.test.entity.ProductsListTest;

import java.util.List;

public interface IProductDAOTest {
    public List<ProductTest> getAllProductsFromDataBase();
    public void saveProductToDataBase(ProductTest productTest);
    public ProductTest getProductFromDataBaseByID(int id);
    public void deleteProductFromDataBaseByID(int id);

    public List<ProductsListTest> getAllProductsListFromDataBase();
}
