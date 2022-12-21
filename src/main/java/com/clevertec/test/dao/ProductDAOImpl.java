package com.clevertec.test.dao;

import com.clevertec.test.entity.Product;
import com.clevertec.test.entity.ProductsList;
import com.clevertec.test.exception.InvalidIDException;
import com.clevertec.test.exception.InvalidProductException;
import com.clevertec.test.utility.SmartFileScanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class ProductDAOImpl implements IProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public ProductDAOImpl() {
    }

    @Override
    public List<Product> getAllProductsFromDataBase() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public void saveProductToDataBase(Product product) {
        if (product.isEmpty() || product.getDiscount() < 0 || product.getName().isEmpty()
                || product.getDiscountQuantity() < 0){
            throw new InvalidProductException();
        }
        if (product.getId() < 0){
            throw new InvalidIDException();
        }

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
    }

    @Override
    public Product getProductFromDataBaseByID(int id) {
        if(id <= 0){
            throw new InvalidIDException("The product with ID " + id + " does not exist");
        }

        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);

        return product;
    }

    @Override
    public void deleteProductFromDataBaseByID(int id) {
        if(id <= 0){
            throw new InvalidIDException("The product with ID " + id + " does not exist");
        }

        Session session = sessionFactory.getCurrentSession();
        Query<Product> query = session.createQuery
                ("delete from Product where id =:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<ProductsList> getAllProductsListFromDataBase() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ProductsList", ProductsList.class).getResultList();
    }
}
