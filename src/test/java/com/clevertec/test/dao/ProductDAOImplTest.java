package com.clevertec.test.dao;

import com.clevertec.test.entity.ProductTest;
import com.clevertec.test.entity.ProductsListTest;
import com.clevertec.test.exception.InvalidIDException;
import com.clevertec.test.exception.InvalidIDExceptionTest;
import com.clevertec.test.exception.InvalidProductExceptionTest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class ProductDAOImplTest implements IProductDAOTest {
    public ProductDAOImplTest() {
    }

    @Override
    public List<ProductTest> getAllProductsFromDataBase() {
        List<ProductTest> productsTest = new ArrayList<>();
        productsTest.add(new ProductTest(1, "potato"
                , 10, 10, 5.5));
        productsTest.add(new ProductTest(2, "chocolate"
                , 5, 5, 3.1));
        productsTest.add(new ProductTest(3, "milk"
                , 0, 0, 1.9));

        return productsTest;
    }

    @Override
    public void saveProductToDataBase(ProductTest productTest) {
        if (productTest.isEmpty() || productTest.getDiscount() < 0 || productTest.getName().isEmpty()
                || productTest.getDiscountQuantity() < 0){
            throw new InvalidProductExceptionTest();
        }
        if (productTest.getId() < 0){
            throw new InvalidIDExceptionTest();
        }
    }

    @Override
    public ProductTest getProductFromDataBaseByID(int id) {
        if(id <= 0){
            throw new InvalidIDExceptionTest("The product with ID " + id + " does not exist");
        }

        return new ProductTest();
    }

    @Override
    public void deleteProductFromDataBaseByID(int id) {
        if(id <= 0){
            throw new InvalidIDExceptionTest("The product with ID " + id + " does not exist");
        }
    }

    @Override
    public List<ProductsListTest> getAllProductsListFromDataBase() {
        throw new InvalidProductExceptionTest();
    }
}
