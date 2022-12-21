package com.clevertec.test.service;

import com.clevertec.test.dao.DiscountDAOImplTest;
import com.clevertec.test.dao.IDiscountDAOTest;
import com.clevertec.test.dao.IProductDAOTest;
import com.clevertec.test.dao.ProductDAOImplTest;
import com.clevertec.test.entity.DiscountCardTest;
import com.clevertec.test.entity.ProductTest;
import com.clevertec.test.entity.ProductsListTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductAndDiscountServiceImplTest implements IProductAndDiscountServiceTest {

    private IDiscountDAOTest discountDAO;

    private IProductDAOTest productDAO;

    public ProductAndDiscountServiceImplTest(){
        discountDAO = new DiscountDAOImplTest();
        productDAO = new ProductDAOImplTest();
    }

    @Override
    @Transactional
    public List<ProductTest> getAllProductsFromDataBase() {
        return productDAO.getAllProductsFromDataBase();
    }

    @Override
    @Transactional
    public void saveProductToDataBase(ProductTest productTest) {
        productDAO.saveProductToDataBase(productTest);
    }

    @Override
    @Transactional
    public ProductTest getProductFromDataBaseByID(int id) {
        return productDAO.getProductFromDataBaseByID(id);
    }

    @Override
    @Transactional
    public void deleteProductFromDataBaseByID(int id) {
        productDAO.deleteProductFromDataBaseByID(id);
    }

    @Override
    @Transactional
    public List<ProductsListTest> getAllProductsListFromDataBase() {
        return productDAO.getAllProductsListFromDataBase();
    }

    @Override
    @Transactional
    public List<DiscountCardTest> getAllDiscountCardsFromDataBase() {
        return discountDAO.getAllDiscountCardsFromDataBase();
    }

    @Override
    @Transactional
    public DiscountCardTest getDiscountCardFromDataBaseByID(int id) {
        return discountDAO.getDiscountCardFromDataBaseByID(id);
    }

    @Override
    @Transactional
    public void saveDiscountCardToDataBase(DiscountCardTest discountCardTest) {
        discountDAO.saveDiscountCardToDataBase(discountCardTest);
    }

    @Override
    @Transactional
    public void deleteDiscountCardFromDataBaseByCardNumber(int cardNumber) {
        discountDAO.deleteDiscountCardFromDataBaseByID(cardNumber);
    }

    @Override
    @Transactional
    public DiscountCardTest getDiscountCardFromDataBaseByCardNumber(int cardNumber) {
        return discountDAO.getDiscountCardFromDataBaseByCardNumber(cardNumber);
    }
}
