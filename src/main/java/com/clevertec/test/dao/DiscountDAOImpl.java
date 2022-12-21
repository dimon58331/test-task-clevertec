package com.clevertec.test.dao;

import com.clevertec.test.entity.DiscountCard;
import com.clevertec.test.entity.Product;
import com.clevertec.test.exception.InvalidDiscountCardException;
import com.clevertec.test.exception.InvalidIDException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DiscountDAOImpl implements IDiscountDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<DiscountCard> getAllDiscountCardsFromDataBase() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("from DiscountCard ", DiscountCard.class).getResultList();
    }

    @Override
    public DiscountCard getDiscountCardFromDataBaseByID(int id) {
        if (id <= 0){
            throw new InvalidIDException();
        }

        Session session = sessionFactory.getCurrentSession();

        return session.get(DiscountCard.class, id);
    }

    @Override
    public void saveDiscountCardToDataBase(DiscountCard discountCard) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(discountCard);
    }

    @Override
    public void deleteDiscountCardFromDataBaseByID(int id) {
        if(id <= 0){
            throw new InvalidIDException("The product with ID " + id + " does not exist");
        }

        Session session = sessionFactory.getCurrentSession();

        Query<Product> query = session.createQuery
                ("delete from DiscountCard where id =:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public DiscountCard getDiscountCardFromDataBaseByCardNumber(int cardNumber) {
        Session session = sessionFactory.getCurrentSession();

        if (cardNumber <= 0){
            throw new InvalidDiscountCardException();
        }

        try {
            return (DiscountCard) session
                    .createQuery("from DiscountCard where numberCard =:number_card")
                    .setParameter("number_card", cardNumber).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
}
