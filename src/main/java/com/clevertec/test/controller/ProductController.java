package com.clevertec.test.controller;

import com.clevertec.test.entity.Product;
import com.clevertec.test.service.IProductAndDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductAndDiscountService productAndDiscountService;

    @GetMapping("/info")
    public String getInstruction(){
        String webInstruction = "";

        webInstruction += "<h3>products/info --- information about requests, GET <br></h3>";
        webInstruction += "<h3>products/ --- get all products from data base, GET <br></h3>";
        webInstruction += "<h3>products/{id} --- get product by id from data base, GET <br></h3>";
        webInstruction += "<h3>products/{id} --- delete product by id from data base, DELETE <br></h3>";

        webInstruction += "<h3>products/ --- save product into data base, POST, request body " +
                "-> Product product <br></h3>";
        webInstruction += "<h3>products/ --- update product into data base, PUT, request body " +
                "-> Product product <br></h3>";


        return webInstruction;
    }

    @GetMapping("/")
    public List<Product> getAllProducts(){
        return productAndDiscountService.getAllProductsFromDataBase();
    }

    @GetMapping("/{id}")
    public Product getProductByID(@PathVariable int id){
        return productAndDiscountService.getProductFromDataBaseByID(id);
    }

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
