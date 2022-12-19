package com.clevertec.test.dao;

import com.clevertec.test.entity.Product;
import com.clevertec.test.entity.ProductsList;

import java.util.List;

public interface IProductDAO {
    public List<Product> getAllProductsFromDataBase();
    public void saveProductToDataBase(Product product);
    public Product getProductFromDataBaseByID(int id);
    public void deleteProductFromDataBaseByID(int id);

    public List<ProductsList> getAllProductsListFromDataBase();
}
