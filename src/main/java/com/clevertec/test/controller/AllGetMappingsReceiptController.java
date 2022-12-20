package com.clevertec.test.controller;

import com.clevertec.test.builder.DescriptionReceiptRegisterServiceBuilder;
import com.clevertec.test.builder.ReceiptRegisterServiceBuilder;
import com.clevertec.test.service.IReceiptRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/receipt")
public class AllGetMappingsReceiptController{
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

        webInstruction += "<h3>receipt?id={value}(required = false)" +
                "&quantity={value}  --- add product to receipt, GET <br></h3>";
        webInstruction += "<h3>receipt/download --- save receipt to file, GET <br></h3>";
        webInstruction += "<h3>receipt/delete?id={value}(required = false)&quantity={value}" +
                " --- delete product from receipt, GET <br></h3>";



        webInstruction += "<h3>receipt/ --- save receipt to file, POST, request body " +
                "-> Map<Product, Integer> <br></h3>";
        webInstruction += "<h3>receipt/ --- update receipt, PUT, request body " +
                "-> Map<Product, Integer> <br></h3>";
        webInstruction += "<h3>receipt/ --- delete product from receipt, DELETE, request body " +
                "-> Map<Product, Integer> <br></h3>";


        return webInstruction;
    }

    @GetMapping("")
    public String saveProductToReceipt(@RequestParam("id") int id
            , @RequestParam(value = "quantity", required = false) String quantity){
        if (quantity == null){
            quantity = "1";
            receiptRegisterService.addProductToReceiptByID(id, Integer.parseInt(quantity));
        }
        else{
            receiptRegisterService.addProductToReceiptByID(id, Integer.parseInt(quantity));
        }

        return "The product with ID: " + id + ", QUANTITY: " + quantity + " was added to receipt!";
    }

    @GetMapping("/")
    public String getReceipt(){
        String receiptInfo = receiptRegisterService.createFullReceipt();

        String webReceiptInfo = receiptInfo.replaceFirst("~", "<pre>")
                .replaceFirst("/~", "</pre>");

        return webReceiptInfo;
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

    @GetMapping("/download")
    @ResponseBody
    public void downloadReceipt(HttpServletResponse response) throws IOException {
        String file = receiptRegisterService.createFullReceipt().
                replaceFirst("~", "").replaceFirst("/~", "");

        response.setContentType("application/txt");
        response.setHeader("Content-Disposition", "attachment; filename=" + "receipt.txt");
        response.setHeader("Content-Length", String.valueOf(file.length()));

        FileCopyUtils.copy(file.getBytes(), response.getOutputStream());
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
