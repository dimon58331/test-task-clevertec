package com.clevertec.test.controller;

import com.clevertec.test.entity.Product;
import com.clevertec.test.service.IProductAndDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class AllGetMappingsProductController{
    @Autowired
    private IProductAndDiscountService productAndDiscountService;
    @GetMapping("/info")
    public String getInstruction(){
        String webInstruction = "";

        webInstruction += "<h3>products/info --- information about requests, GET <br></h3>";
        webInstruction += "<h3>products/ --- get all products from data base, GET <br></h3>";
        webInstruction += "<h3>products/{id} --- get product by id from data base, GET <br></h3>";
        webInstruction += "<h3>products?id={value}(required = false)&name={value}&discount={value}" +
                "&quantity_for_discount={value}" +
                "&price={value} --- add or update product to data base, GET <br></h3>";
        webInstruction += "<h3>products/delete?id={value} " +
                "--- delete product from data base by ID, GET <br></h3>";


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

    @GetMapping("")
    public List<Product> saveOrUpdateProductToDB(
            @RequestParam(value = "id", required = false) String id
            , @RequestParam("name") String name, @RequestParam("discount") double discount
            , @RequestParam("quantity_for_discount") int quantityForDiscount
            , @RequestParam("price") double price
    ){
        Product product = null;
        if (id == null){
            product = new Product(0, name, discount, quantityForDiscount, price);
        }else{
            product = new Product(Integer.parseInt(id), name, discount, quantityForDiscount, price);
        }

        productAndDiscountService.saveProductToDataBase(product);

        return productAndDiscountService.getAllProductsFromDataBase();
    }

    @GetMapping("/delete")
    public Product deleteProductFromDataBaseByID(@RequestParam("id") int id){
        Product product = productAndDiscountService.getProductFromDataBaseByID(id);
        productAndDiscountService.deleteProductFromDataBaseByID(id);

        return product;
    }
}
