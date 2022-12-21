package com.clevertec.test.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "discount_card")
@Component
public class DiscountCardTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_card_id")
    private int id;

    @Column(name = "number_card")
    private int numberCard;

    @Column(name = "discount")
    private double discount;

    @Column(name = "active")
    private boolean active = false;

    public DiscountCardTest() {
    }

    public DiscountCardTest(int numberCard, double discount, boolean active) {
        this.numberCard = numberCard;
        this.discount = discount;
        this.active = active;
    }

    public int getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(int numberCard) {
        this.numberCard = numberCard;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount * 0.01;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DiscountCardTest{" +
                "id=" + id +
                ", numberCard=" + numberCard +
                ", discount=" + discount +
                ", active=" + active +
                '}';
    }
}
