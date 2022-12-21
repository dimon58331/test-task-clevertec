package com.clevertec.test.controller;

import com.clevertec.test.builder.DescriptionReceiptRegisterServiceBuilder;
import com.clevertec.test.builder.ReceiptRegisterServiceBuilder;
import com.clevertec.test.entity.DiscountCard;
import com.clevertec.test.service.IProductAndDiscountService;
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

    @Autowired
    private IProductAndDiscountService productAndDiscountService;

    @GetMapping("/info")
    public String getInstruction(){
        String webInstruction = "";

        webInstruction += "<h3>receipt/info --- information about requests, GET <br></h3>";
        webInstruction += "<h3>receipt/ --- print receipt, GET <br></h3>";
        webInstruction += "<h3>receipt/db --- add products to receipt from DataBase, GET <br></h3>";

        webInstruction += "<h3>receipt?id={value}(required = false)" +
                "&quantity={value}" +
                "&discount_card_number={value}(required = false)" +
                "  --- add product to receipt, GET <br></h3>";
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
    public String saveProductToReceipt(@RequestParam(value = "id", required = false) String id
            , @RequestParam(value = "quantity", required = false, defaultValue = "1") String quantity
            , @RequestParam(value = "discount_card_number", required = false) String discountCardNumber){

        String message = "";

        if (id != null && !id.isEmpty()){
            receiptRegisterService
                    .addProductToReceiptByID(Integer.parseInt(id), Integer.parseInt(quantity));

            message += "The product with ID: " + id
                    + ", QUANTITY: " + quantity + " was added to receipt!";
        }

        if (discountCardNumber != null){
            DiscountCard discountCard = productAndDiscountService
                    .getDiscountCardFromDataBaseByCardNumber(Integer.parseInt(discountCardNumber));
            if (discountCard != null){
                receiptRegisterService.addDiscountCardToReceipt(discountCard);
                message += "Discount card was added: " + discountCard;
            }else {
                message += "Invalid card number!";
            }
        }

        return message.isEmpty() ? "<h2>YOU DID NOT ENTER THE VALUES!!!</h2>" : message;
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
            , @RequestParam(value = "quantity", required = false, defaultValue = "1") String quantity){

        receiptRegisterService.deleteProductFromReceiptByID(id, Integer.parseInt(quantity));

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
        ReceiptRegisterServiceBuilder receiptRegisterServiceBuilder
                = new ReceiptRegisterServiceBuilder();

        descriptionReceiptRegisterServiceBuilder
                .initializeReceiptRegisterServiceBuilder(receiptRegisterServiceBuilder);

        receiptRegisterService
                = receiptRegisterServiceBuilder.getReceiptRegisterServiceWithParameters();

        receiptRegisterService.createFullReceipt();

        return "Success! The receipt was created with parameters from DataBase!";
    }
}
