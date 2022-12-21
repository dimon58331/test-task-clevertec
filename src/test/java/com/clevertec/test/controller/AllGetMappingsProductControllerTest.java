package com.clevertec.test.controller;

import com.clevertec.test.entity.ProductTest;
import com.clevertec.test.exception.InvalidIDExceptionTest;
import com.clevertec.test.exception.InvalidProductExceptionTest;
import com.clevertec.test.service.IProductAndDiscountServiceTest;
import com.clevertec.test.service.ProductAndDiscountServiceImplTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class AllGetMappingsProductControllerTest {

    @Autowired
    private IProductAndDiscountServiceTest productAndDiscountService;

    public AllGetMappingsProductControllerTest() {
        productAndDiscountService = new ProductAndDiscountServiceImplTest();
    }

    @Nested
    class TestsWithParameters{
        @ParameterizedTest
        @CsvSource({
                "0",
                "-1"
        })
        @GetMapping("/{id}")
        @DisplayName("throw ID EXCEPTION if incorrect parameters")
        public void getProductByID(String id){
            if (id == null){
                id = "0";
            }

            try {
                ProductTest productTest = productAndDiscountService
                        .getProductFromDataBaseByID(Integer.parseInt(id));

                Assertions.assertThat(false).isTrue();
            }catch (Exception e){
                Assertions.assertThat(e)
                        .isInstanceOf(InvalidIDExceptionTest.class);
            }
        }

        @ParameterizedTest
        @CsvSource({
                ",,0,0,0",
                ",potato, -2, 2, 2",
                "-3, potato, 2, -2, 2",
                ",potato, 2, 2, -2",
        })
        @GetMapping("")
        @DisplayName("throw ID or PRODUCT EXCEPTION if incorrect parameters")
        public void saveOrUpdateProductToDB(String id, String name, double discount
                , int quantityForDiscount, double price){

            ProductTest productTest = null;
            if (id == null){
                productTest = new ProductTest(0, name, discount, quantityForDiscount, price);
            }else{
                productTest = new ProductTest(Integer.parseInt(id), name
                        , discount, quantityForDiscount, price);
            }

            try {
                productAndDiscountService.saveProductToDataBase(productTest);
                Assertions.assertThat(false).isTrue();
            }catch (Exception e){
                Assertions.assertThat(e).isInstanceOfAny(InvalidIDExceptionTest.class
                        , InvalidProductExceptionTest.class);
            }

        }

        @ParameterizedTest
        @CsvSource({
                "0",
                "-1"
        })
        @GetMapping("/delete")
        @DisplayName("throw ID exception if incorrect parameters")
        public void deleteProductFromDataBaseByID(String id){
            if (id == null){
                id = "0";
            }

            try {
                ProductTest productTest = productAndDiscountService
                        .getProductFromDataBaseByID(Integer.parseInt(id));
                productAndDiscountService.deleteProductFromDataBaseByID(Integer.parseInt(id));
            }catch (Exception e){
                Assertions.assertThat(e).isInstanceOf(InvalidIDExceptionTest.class);
            }
        }
    }

    @Test
    @GetMapping("/info")
    @DisplayName("get instruction if web instruction is not empty")
    public void getInstruction(){
        String webInstruction = "";

        webInstruction += "<h3>products/info --- information about requests, GET <br></h3>";
        webInstruction += "<h3>products/ --- get all products from data base, GET <br></h3>";
        webInstruction += "<h3>products/{id} --- get product by id from data base, GET <br></h3>";
        webInstruction += "<h3>products?id={value}(required = false)&name={value}&discount={value}" +
                "&quantity_for_discount={value}" +
                "&price={value} --- add or update product to data base, GET <br></h3>";
        webInstruction += "<h3>products/delete?id={value} " +
                "--- delete product from data base by ID, GET <br></h3>";


        webInstruction += "<h3>products/{id} " +
                "--- delete product by id from data base, DELETE <br></h3>";
        webInstruction += "<h3>products/ --- save product into data base, POST, request body " +
                "-> ProductTest product <br></h3>";
        webInstruction += "<h3>products/ --- update product into data base, PUT, request body " +
                "-> ProductTest product <br></h3>";


        Assertions.assertThat(webInstruction).isNotEmpty();
    }

    @Test
    @GetMapping("/")
    @DisplayName("get all products from DB if DB is not empty")
    public void getAllProducts(){
        Assertions.assertThat(productAndDiscountService.getAllProductsFromDataBase()).isNotEmpty();
    }

}
