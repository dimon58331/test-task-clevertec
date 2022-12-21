package com.clevertec.test.dao;

import com.clevertec.test.entity.DiscountCardTest;
import com.clevertec.test.entity.ProductTest;
import com.clevertec.test.exception.InvalidDiscountCardException;
import com.clevertec.test.exception.InvalidDiscountCardExceptionTest;
import com.clevertec.test.exception.InvalidIDExceptionTest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DiscountDAOImplTest implements IDiscountDAOTest {

    @Override
    public List<DiscountCardTest> getAllDiscountCardsFromDataBase() {
        List<DiscountCardTest> discountCardTests = new ArrayList<>(){
            {add(new DiscountCardTest(1234, 6, true));}
        };

        return discountCardTests;
    }

    @Override
    public DiscountCardTest getDiscountCardFromDataBaseByID(int id) {
        if (id == 1) {
            return new DiscountCardTest(1234, 6, true);
        }
        return null;
    }

    @Override
    public void saveDiscountCardToDataBase(DiscountCardTest discountCardTest) {
        System.out.println("The discount card:" + discountCardTest + " was saved into DB!");
    }

    @Override
    public void deleteDiscountCardFromDataBaseByID(int id) {
        if(id <= 0){
            throw new InvalidIDExceptionTest("The product with ID " + id + " does not exist");
        }
        System.out.println("The product with ID:" + id + ", was deleted from DB!");
    }

    @Override
    public DiscountCardTest getDiscountCardFromDataBaseByCardNumber(int cardNumber) {
        if (cardNumber != 1234){
            throw new InvalidDiscountCardExceptionTest();
        }

        return new DiscountCardTest(1234, 6, true);
    }
}
