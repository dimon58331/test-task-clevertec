package com.clevertec.test.builder;

import com.clevertec.test.entity.DiscountCard;
import com.clevertec.test.entity.ProductsList;
import com.clevertec.test.service.IProductAndDiscountService;
import com.clevertec.test.service.IReceiptRegisterService;
import com.clevertec.test.service.ProductAndDiscountServiceImpl;
import com.clevertec.test.service.ReceiptRegisterServiceImpl;
import com.clevertec.test.utility.SmartFileScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DescriptionReceiptRegisterServiceBuilder {
    private SmartFileScanner smartFileScanner;

    @Autowired
    private IProductAndDiscountService productAndDiscountService;
    @Autowired
    private ReceiptRegisterServiceImpl receiptRegisterService;

    public DescriptionReceiptRegisterServiceBuilder(){
    }

    public void initializeReceiptRegisterServiceBuilder
            (ReceiptRegisterServiceBuilder receiptRegisterServiceBuilder
                    , SmartFileScanner smartFileScanner){
        this.smartFileScanner = smartFileScanner;

        initializeReceiptRegisterServiceByAllParametersFromFile(receiptRegisterService);

        receiptRegisterServiceBuilder.setReceiptRegisterService(receiptRegisterService);
    }

    public void initializeReceiptRegisterServiceBuilder
            (ReceiptRegisterServiceBuilder receiptRegisterServiceBuilder){

        initializeReceiptRegisterServiceByAllParametersFromDataBase(receiptRegisterService);

        receiptRegisterServiceBuilder.setReceiptRegisterService(receiptRegisterService);
    }



    private void initializeReceiptRegisterServiceByAllParametersFromFile
            (IReceiptRegisterService receiptRegisterService){
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

            DiscountCard discountCard = new DiscountCard();
            discountCard.setNumberCard((Integer) productParameters.get(numberCardID).get(0));
            discountCard.setDiscount( (Integer) productParameters.get(discountID).get(1));
            discountCard.setActive(true);

            receiptRegisterService.addDiscountCardToReceipt(discountCard);
        }
    }

    private void initializeReceiptRegisterServiceByAllParametersFromDataBase
            (IReceiptRegisterService receiptRegisterService){

        List<ProductsList> productsLists = productAndDiscountService.getAllProductsListFromDataBase();

        for (ProductsList productsList : productsLists){
            receiptRegisterService.addProductToReceiptByID(productsList.getProductID()
                    , productsList.getQuantity());
        }

        if (!productAndDiscountService.getAllDiscountCardsFromDataBase().isEmpty()){
            DiscountCard discountCard = productAndDiscountService.getDiscountCardFromDataBaseByID(1);
            receiptRegisterService.addDiscountCardToReceipt(discountCard);
        }
    }

}
