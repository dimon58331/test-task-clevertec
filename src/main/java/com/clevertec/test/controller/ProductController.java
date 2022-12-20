package com.clevertec.test.controller;

import com.clevertec.test.entity.Product;
import com.clevertec.test.service.IProductAndDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductAndDiscountService productAndDiscountService;

    @PostMapping("/")
    public Product saveProductToDB(@RequestBody Product product){
        productAndDiscountService.saveProductToDataBase(product);

        return product;
    }

    @PutMapping("/")
    public Product updateProductToDB(@RequestBody Product product){
        productAndDiscountService.saveProductToDataBase(product);

        return product;
    }

    @DeleteMapping("/{id}")
    public String deleteProductFromDB(@PathVariable int id){
        productAndDiscountService.deleteProductFromDataBaseByID(id);

        return "The product with ID: " + id + " was deleted from DB!";
    }
}
