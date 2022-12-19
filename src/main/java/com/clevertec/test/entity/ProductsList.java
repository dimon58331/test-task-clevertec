package com.clevertec.test.entity;

import javax.persistence.*;

@Entity
@Table(name = "products_list")
public class ProductsList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "product_id")
    private int productID;

    @Column(name = "quantity")
    private int quantity;

    public ProductsList() {
    }

    public ProductsList(int productID, int quantity) {
        this.productID = productID;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductsList{" +
                "id=" + id +
                ", productID=" + productID +
                ", quantity=" + quantity +
                '}';
    }
}
