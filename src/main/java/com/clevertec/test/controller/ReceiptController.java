package com.clevertec.test.controller;

import com.clevertec.test.builder.DescriptionReceiptRegisterServiceBuilder;
import com.clevertec.test.entity.Product;
import com.clevertec.test.service.IReceiptRegisterService;
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
}
