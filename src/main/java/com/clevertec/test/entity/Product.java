package com.clevertec.test.entity;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_discount")
    private double discount;

    @Column(name = "product_discount_quantity")
    private int discountQuantity = 5;

    @Column(name = "product_price")
    private double price;

    public Product() {
    }

    public Product(int id, String name, double discount, int discountQuantity, double price) {
        this.id = id;
        this.name = name;
        this.discount = discount;
        this.discountQuantity = discountQuantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isEmpty() {
        if (name == null){
            return true;
        } else return price <= 0;
    }

    public int getDiscountQuantity() {
        return discountQuantity;
    }

    public void setDiscountQuantity(int discountQuantity) {
        this.discountQuantity = discountQuantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", discount=" + discount +
                ", discountQuantity=" + discountQuantity +
                ", price=" + price +
                '}';
    }
}
