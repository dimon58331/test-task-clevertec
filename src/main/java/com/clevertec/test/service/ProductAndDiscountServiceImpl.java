package com.clevertec.test.service;

import com.clevertec.test.dao.DiscountDAOImpl;
import com.clevertec.test.dao.IDiscountDAO;
import com.clevertec.test.dao.IProductDAO;
import com.clevertec.test.dao.ProductDAOImpl;
import com.clevertec.test.entity.DiscountCard;
import com.clevertec.test.entity.Product;
import com.clevertec.test.entity.ProductsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductAndDiscountServiceImpl implements IProductAndDiscountService {

    @Autowired
    private IDiscountDAO discountDAO;
    @Autowired
    private IProductDAO productDAO;

    public ProductAndDiscountServiceImpl(){
    }

    @Override
    @Transactional
    public List<Product> getAllProductsFromDataBase() {
        return productDAO.getAllProductsFromDataBase();
    }

    @Override
    @Transactional
    public void saveProductToDataBase(Product product) {
        productDAO.saveProductToDataBase(product);
    }

    @Override
    @Transactional
    public Product getProductFromDataBaseByID(int id) {
        return productDAO.getProductFromDataBaseByID(id);
    }

    @Override
    @Transactional
    public void deleteProductFromDataBaseByID(int id) {
        productDAO.deleteProductFromDataBaseByID(id);
    }

    @Override
    @Transactional
    public List<ProductsList> getAllProductsListFromDataBase() {
        return productDAO.getAllProductsListFromDataBase();
    }

    @Override
    @Transactional
    public List<DiscountCard> getAllDiscountCardsFromDataBase() {
        return discountDAO.getAllDiscountCardsFromDataBase();
    }

    @Override
    @Transactional
    public DiscountCard getDiscountCardFromDataBaseByID(int id) {
        return discountDAO.getDiscountCardFromDataBaseByID(id);
    }

    @Override
    @Transactional
    public void saveDiscountCardToDataBase(DiscountCard discountCard) {
        discountDAO.saveDiscountCardToDataBase(discountCard);
    }

    @Override
    @Transactional
    public void deleteDiscountCardFromDataBaseByCardNumber(int cardNumber) {
        discountDAO.deleteDiscountCardFromDataBaseByID(cardNumber);
    }
}
