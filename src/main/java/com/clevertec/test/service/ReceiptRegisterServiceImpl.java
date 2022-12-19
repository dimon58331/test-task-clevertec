package com.clevertec.test.service;

import com.clevertec.test.entity.DiscountCard;
import com.clevertec.test.entity.Receipt;
import com.clevertec.test.entity.Product;
import com.clevertec.test.exception.*;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.IllegalFormatCodePointException;
import java.util.List;

@Service
public class ReceiptRegisterServiceImpl implements IReceiptRegisterService {

    @Autowired
    private Receipt receipt;

    @Autowired
    private DiscountCard discountCard;

    @Autowired
    private IProductAndDiscountService productAndDiscountService;

    public ReceiptRegisterServiceImpl() {
    }

    private void addProduct(int id, int quantity){
        if (quantity <= 0){
            throw new InvalidQuantityException();
        }

        if (receipt.getProducts().containsKey(id)){
            receipt.getProducts().replace(id, quantity + receipt.getProducts().get(id));
        } else {
            receipt.getProducts().put(id, quantity);
        }
    }

    private void deleteProduct(int id, int quantity){
        if (quantity <= 0){
            throw new InvalidQuantityException();
        }

        if (receipt.getProducts().containsKey(id)){
            if (quantity == receipt.getProducts().get(id)){
                receipt.getProducts().remove(id);
            }else{
                receipt.getProducts().replace(id, receipt.getProducts().get(id) - quantity);
            }
        }else{
            throw new InvalidIDException();
        }
    }

    @Override
    public String createFullReceipt() {

        Date date = new Date();

        receipt.setDate(date.toString());
        receipt.setTotalPrice(0);
        receipt.setTotalPriceWithDiscount(0);
        receipt.setTotalDiscount(0);

        String receiptInfo = "~";

        DecimalFormat df = new DecimalFormat("#.##");

        receiptInfo += "DATE: " + receipt.getDate();

        receiptInfo += "\nQUANTITY  NAME                 PRICE      TOTAL PRICE       PRICE WITH DISCOUNT \n";
        receiptInfo += "-------------------------------------------------------------------------------\n";

        for(int id : receipt.getProducts().keySet()){

            Product product = productAndDiscountService.getProductFromDataBaseByID(id);
            if (receipt.getProducts().get(id) <= product.getDiscountQuantity()
                    || product.getDiscount() == 0) {
                product.setDiscount(discountCard.getDiscount());
            }

            double productPrice = product.getPrice() * receipt.getProducts().get(id);
            double productPriceWithDiscount = (product.getPrice() - (product.getDiscount() / 100)
                    * product.getPrice()) * receipt.getProducts().get(id);


            receiptInfo += String.format("%-9s %-20s %-10s %-17s %-10s %n"
                    , df.format(receipt.getProducts().get(id))
                    , product.getName(), df.format(product.getPrice())
                    , df.format(productPrice)
                    , df.format(productPriceWithDiscount));

            receipt.setTotalPrice(productPrice + receipt.getTotalPrice());

            receipt.setTotalPriceWithDiscount(productPriceWithDiscount + receipt.getTotalPriceWithDiscount());

            receipt.setTotalDiscount(receipt.getTotalDiscount()
                    + (receipt.getTotalPrice() - receipt.getTotalPriceWithDiscount()));
        }
        receiptInfo += "-------------------------------------------------------------------------------\n";
        receiptInfo += "-------------------------------------------------------------------------------\n";

        if (discountCard != null && discountCard.isActive()){
            receiptInfo += "DISCOUNT CARD ID = " + discountCard.getNumberCard() + ", DISCOUNT = "
                    + String.format("%s", df.format(discountCard.getDiscount())) + "%\n";
        }

        receiptInfo += "Total price - " + df.format(receipt.getTotalPrice()) + "\n";
        receiptInfo += "TOTAL PRICE (with sale)* - "
                + df.format(receipt.getTotalPriceWithDiscount()) + "\n";
        receiptInfo += "\n* - sale product \n(the discount card does not work for promotional products)\n";

        receiptInfo += "-------------------------------------------------------------------------------\n/~";

        return receiptInfo;
    }

    @Override
    public void addProductToReceiptByID(int id, int quantity){
        addProduct(id, quantity);
    }

    @Override
    public void deleteProductFromReceiptByID(int id, int quantity) {
        deleteProduct(id, quantity);
    }

    @Override
    public void addDiscountCardToReceipt(DiscountCard discountCard) {
        if (discountCard == null || discountCard.getNumberCard() == 0){
            throw new InvalidDiscountCardException();
        }
        this.discountCard = discountCard;
    }

    @Override
    public Receipt getReceipt() {
        return receipt;
    }

    @Override
    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}
