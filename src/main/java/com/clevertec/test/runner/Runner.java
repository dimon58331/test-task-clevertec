//package com.clevertec.test.runner;
//
//import com.clevertec.test.builder.DescriptionReceiptRegisterServiceBuilder;
//import com.clevertec.test.builder.ReceiptRegisterServiceBuilder;
//import com.clevertec.test.entity.Receipt;
//import com.clevertec.test.service.IProductAndDiscountService;
//import com.clevertec.test.service.IReceiptRegisterService;
//import com.clevertec.test.service.ProductAndDiscountServiceImpl;
//import com.clevertec.test.utility.SmartFileScanner;
//import org.springframework.beans.factory.annotation.Autowired;
//
//
//public class Runner {
//    public static void main(String[] args) throws Exception {
//
////        SmartFileScanner smartFileScanner = new SmartFileScanner(args[0]);
//
//        IProductAndDiscountService iProductAndDiscountService = new ProductAndDiscountServiceImpl();
//
//        iProductAndDiscountService.deleteDiscountCardFromDataBaseByCardNumber(1);
//
//        ReceiptRegisterServiceBuilder serviceBuilder = new ReceiptRegisterServiceBuilder();
//
//        DescriptionReceiptRegisterServiceBuilder descriptionReceiptRegisterServiceBuilder
//                = new DescriptionReceiptRegisterServiceBuilder();
//
//        descriptionReceiptRegisterServiceBuilder.initializeReceiptRegisterServiceBuilder(serviceBuilder);
//
//        IReceiptRegisterService receiptRegisterService
//                = serviceBuilder.getReceiptRegisterServiceWithParameters();
//
//        SmartFileScanner.saveToFile(receiptRegisterService.createFullReceipt(), "receipt.txt");
//    }
//}
