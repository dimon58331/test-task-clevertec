package com.clevertec.test.builder;

import com.clevertec.test.entity.DiscountCardTest;
import com.clevertec.test.entity.ProductsListTest;
import com.clevertec.test.service.IProductAndDiscountServiceTest;
import com.clevertec.test.service.IReceiptRegisterServiceTest;
import com.clevertec.test.service.ProductAndDiscountServiceImplTest;
import com.clevertec.test.service.ReceiptRegisterServiceImplTest;
import com.clevertec.test.utility.SmartFileScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DescriptionReceiptRegisterServiceBuilderTest {
    private SmartFileScanner smartFileScanner;
//    private IProductAndDiscountServiceTest productAndDiscountService;

    private ReceiptRegisterServiceImplTest receiptRegisterService;

    public DescriptionReceiptRegisterServiceBuilderTest(){
        receiptRegisterService = new ReceiptRegisterServiceImplTest();
    }

    public void initializeReceiptRegisterServiceBuilder
            (ReceiptRegisterServiceBuilderTest receiptRegisterServiceBuilderTest
                    , SmartFileScanner smartFileScanner){
        this.smartFileScanner = smartFileScanner;

        initializeReceiptRegisterServiceByAllParametersFromFile(receiptRegisterService);

        receiptRegisterServiceBuilderTest.setReceiptRegisterService(receiptRegisterService);
    }

    public void initializeReceiptRegisterServiceBuilder
            (ReceiptRegisterServiceBuilderTest receiptRegisterServiceBuilderTest){

        initializeReceiptRegisterServiceByAllParametersFromDataBase(receiptRegisterService);

        receiptRegisterServiceBuilderTest.setReceiptRegisterService(receiptRegisterService);
    }



    private void initializeReceiptRegisterServiceByAllParametersFromFile
            (IReceiptRegisterServiceTest receiptRegisterService){
        List<List<Number>> productParameters = smartFileScanner.getNumberParameters("\\s+", "-");

        int discountCardCount = smartFileScanner.getStringParameters("\\s+", "-").size();

        for (int i = 0; i < productParameters.size() - discountCardCount; i++){
            receiptRegisterService.addProductToReceiptByID(
                    (Integer) productParameters.get(i).get(0)
                    , (Integer) productParameters.get(i).get(1)
            );
        }

        if (discountCardCount > 0){
            int numberCardID = productParameters.size() - 1;
            int discountID = productParameters.size() - 1;

            DiscountCardTest discountCardTest = new DiscountCardTest();
            discountCardTest.setNumberCard((Integer) productParameters.get(numberCardID).get(0));
            discountCardTest.setDiscount( (Integer) productParameters.get(discountID).get(1));
            discountCardTest.setActive(true);

            receiptRegisterService.addDiscountCardToReceipt(discountCardTest);
        }
    }

    private void initializeReceiptRegisterServiceByAllParametersFromDataBase
            (IReceiptRegisterServiceTest receiptRegisterService){
        receiptRegisterService.addProductToReceiptByID(1, 6);
        receiptRegisterService.addProductToReceiptByID(2, 5);
        receiptRegisterService.addProductToReceiptByID(3, 4);
    }

}
