package com.clevertec.test.service;

import com.clevertec.test.entity.DiscountCardTest;
import com.clevertec.test.entity.ProductTest;
import com.clevertec.test.entity.ReceiptTest;
import com.clevertec.test.exception.InvalidDiscountCardExceptionTest;
import com.clevertec.test.exception.InvalidIDException;
import com.clevertec.test.exception.InvalidIDExceptionTest;
import com.clevertec.test.exception.InvalidQuantityExceptionTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Date;

@Service
public class ReceiptRegisterServiceImplTest implements IReceiptRegisterServiceTest {

    private ReceiptTest receiptTest;

    private DiscountCardTest discountCardTest;

    private IProductAndDiscountServiceTest productAndDiscountService;

    public ReceiptRegisterServiceImplTest() {
        receiptTest = new ReceiptTest();
        discountCardTest = new DiscountCardTest();
        productAndDiscountService = new ProductAndDiscountServiceImplTest();
    }

    private void addProduct(int id, int quantity){
        if (quantity <= 0){
            throw new InvalidQuantityExceptionTest();
        }
        if (id <= 0){
            throw new InvalidIDExceptionTest();
        }

        if (receiptTest.getProducts().containsKey(id)){
            receiptTest.getProducts().replace(id, quantity + receiptTest.getProducts().get(id));
        } else {
            receiptTest.getProducts().put(id, quantity);
        }
    }

    private void deleteProduct(int id, int quantity){
        if (quantity <= 0){
            throw new InvalidQuantityExceptionTest();
        }

        if (receiptTest.getProducts().containsKey(id)){
            if (quantity == receiptTest.getProducts().get(id)){
                receiptTest.getProducts().remove(id);
            }else{
                receiptTest.getProducts().replace(id, receiptTest.getProducts().get(id) - quantity);
            }
        }else{
            throw new InvalidIDExceptionTest();
        }
    }

    @Override
    public String createFullReceipt() {

        Date date = new Date();

        receiptTest.setDate(date.toString());
        receiptTest.setTotalPrice(0);
        receiptTest.setTotalPriceWithDiscount(0);
        receiptTest.setTotalDiscount(0);

        String receiptInfo = "~";

        DecimalFormat df = new DecimalFormat("#.##");

        receiptInfo += "DATE: " + receiptTest.getDate();

        receiptInfo += "\nQUANTITY  NAME                 PRICE      TOTAL PRICE       PRICE WITH DISCOUNT \n";
        receiptInfo += "-------------------------------------------------------------------------------\n";

        for(int id : receiptTest.getProducts().keySet()){

            ProductTest productTest = productAndDiscountService.getProductFromDataBaseByID(id);

            if (receiptTest.getProducts().get(id) <= productTest.getDiscountQuantity()
                    || productTest.getDiscount() == 0) {
                productTest.setDiscount(discountCardTest.getDiscount());
            }

            double productPrice = productTest.getPrice() * receiptTest.getProducts().get(id);
            double productPriceWithDiscount = (productTest.getPrice() - (productTest.getDiscount() / 100)
                    * productTest.getPrice()) * receiptTest.getProducts().get(id);


            receiptInfo += String.format("%-9s %-20s %-10s %-17s %-10s %n"
                    , df.format(receiptTest.getProducts().get(id))
                    , productTest.getName(), df.format(productTest.getPrice())
                    , df.format(productPrice)
                    , df.format(productPriceWithDiscount));

            receiptTest.setTotalPrice(productPrice + receiptTest.getTotalPrice());

            receiptTest.setTotalPriceWithDiscount(productPriceWithDiscount + receiptTest.getTotalPriceWithDiscount());

            receiptTest.setTotalDiscount(receiptTest.getTotalDiscount()
                    + (receiptTest.getTotalPrice() - receiptTest.getTotalPriceWithDiscount()));
        }
        receiptInfo += "-------------------------------------------------------------------------------\n";
        receiptInfo += "-------------------------------------------------------------------------------\n";

        if (discountCardTest != null && discountCardTest.isActive()){
            receiptInfo += "DISCOUNT CARD NUMBER = " + discountCardTest.getNumberCard() + ", DISCOUNT = "
                    + String.format("%s", df.format(discountCardTest.getDiscount())) + "%\n";
        }

        receiptInfo += "Total price - " + df.format(receiptTest.getTotalPrice()) + "\n";
        receiptInfo += "TOTAL PRICE (with sale)* - "
                + df.format(receiptTest.getTotalPriceWithDiscount()) + "\n";
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
    public void addDiscountCardToReceipt(DiscountCardTest discountCardTest) {
        if (discountCardTest == null || discountCardTest.getNumberCard() == 0){
            throw new InvalidDiscountCardExceptionTest();
        }
        this.discountCardTest = discountCardTest;
    }

    @Override
    public ReceiptTest getReceipt() {
        return receiptTest;
    }

    @Override
    public void setReceipt(ReceiptTest receiptTest) {
        this.receiptTest = receiptTest;
    }
}
