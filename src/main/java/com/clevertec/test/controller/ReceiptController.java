package com.clevertec.test.controller;

import com.clevertec.test.builder.DescriptionReceiptRegisterServiceBuilder;
import com.clevertec.test.builder.ReceiptRegisterServiceBuilder;
import com.clevertec.test.entity.Product;
import com.clevertec.test.service.IReceiptRegisterService;
import com.clevertec.test.utility.SmartFileScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {

    @Autowired
    private DescriptionReceiptRegisterServiceBuilder descriptionReceiptRegisterServiceBuilder;

    @Autowired
    private IReceiptRegisterService receiptRegisterService;

    @GetMapping("/info")
    public String getInstruction(){
        String webInstruction = "";

        webInstruction += "<h3>receipt/info --- information about requests, GET <br></h3>";
        webInstruction += "<h3>receipt/ --- print receipt, GET <br></h3>";
        webInstruction += "<h3>receipt/db --- add products to receipt from DataBase, GET <br></h3>";
        webInstruction += "<h3>receipt/ --- save receipt to file, POST, request body " +
                "-> Map<Product, Integer> <br></h3>";
        webInstruction += "<h3>receipt/ --- update receipt, PUT, request body " +
                "-> Map<Product, Integer> <br></h3>";
        webInstruction += "<h3>receipt/ --- delete product from receipt, DELETE, request body " +
                "-> Map<Product, Integer> <br></h3>";
        webInstruction += "<h3>receipt/delete?id={value} " +
                "or receipt/delete?id={value}&quantity={value}" +
                " --- delete product from receipt, GET <br></h3>";

        return webInstruction;
    }

    @GetMapping("/")
    public String getReceipt(){
        String receiptInfo = receiptRegisterService.createFullReceipt();

        String webReceiptInfo = receiptInfo.replaceFirst("~", "<pre>")
                .replaceFirst("/~", "</pre>");

        return webReceiptInfo;
    }

    @PostMapping("/")
    public Map<Product, Integer> saveProductToReceipt(@RequestBody Map<Product, Integer> products){
        for (Product product : products.keySet()){
            receiptRegisterService.addProductToReceiptByID(product.getId(), products.get(product));
        }

        return products;
    }

    @PutMapping("/")
    public Map<Product, Integer> updateProductToReceipt(@RequestBody Map<Product, Integer> products){
        for (Product product : products.keySet()){
            receiptRegisterService.addProductToReceiptByID(product.getId(), products.get(product));
        }

        return products;
    }

    @DeleteMapping("/")
    public String deleteProductFromReceiptByID(@RequestBody Map<Product, Integer> products){

        for (Product product : products.keySet()){
            receiptRegisterService.deleteProductFromReceiptByID(product.getId(), products.get(product));
        }

        return "These products was removed:\n" + products;
    }

    @GetMapping("/delete")
    public String deleteProductFromReceiptByID(@RequestParam("id") int id
            , @RequestParam(value = "quantity", required = false) String quantity){

        if (quantity == null){
            receiptRegisterService.deleteProductFromReceiptByID(id,1);
        }
        else{
            receiptRegisterService.deleteProductFromReceiptByID(id, Integer.parseInt(quantity));
        }

        return "The product with ID: " + id + ", QUANTITY: " + quantity + " was deleted from receipt!";
    }

    @GetMapping("/save")
    public String saveReceiptToFile(){

        String receiptInfo = receiptRegisterService.createFullReceipt();

        String webReceiptInfo = "The receipt was created and saved to file with name "
                + "\"C:\\Users\\[user_name]\\Desktop\\receipt.txt\" <br><br>";

        webReceiptInfo += receiptInfo.replaceFirst("~", "<pre>")
                .replaceFirst("/~", "</pre>");

        SmartFileScanner.saveToFile(receiptInfo.replaceFirst("~", "")
                .replaceFirst("/~", ""), "receipt.txt");

        return webReceiptInfo;
    }

    @GetMapping("/db")
    public String createDefaultReceiptFromDataBase(){
        ReceiptRegisterServiceBuilder receiptRegisterServiceBuilder = new ReceiptRegisterServiceBuilder();

        descriptionReceiptRegisterServiceBuilder
                .initializeReceiptRegisterServiceBuilder(receiptRegisterServiceBuilder);

        receiptRegisterService = receiptRegisterServiceBuilder.getReceiptRegisterServiceWithParameters();

        receiptRegisterService.createFullReceipt();

        return "Success! The receipt was created with parameters from DataBase!";
    }
}
