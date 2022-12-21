package com.clevertec.test.controller;

import com.clevertec.test.builder.DescriptionReceiptRegisterServiceBuilderTest;
import com.clevertec.test.builder.ReceiptRegisterServiceBuilderTest;
import com.clevertec.test.entity.DiscountCardTest;
import com.clevertec.test.exception.InvalidDiscountCardExceptionTest;
import com.clevertec.test.exception.InvalidIDExceptionTest;
import com.clevertec.test.exception.InvalidQuantityExceptionTest;
import com.clevertec.test.service.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequestMapping("/receipt")
public class AllGetMappingsReceiptControllerTest {

    private DescriptionReceiptRegisterServiceBuilderTest descriptionReceiptRegisterServiceBuilderTest;

    private IReceiptRegisterServiceTest receiptRegisterService;

    private IProductAndDiscountServiceTest productAndDiscountService;

    public AllGetMappingsReceiptControllerTest(){
        descriptionReceiptRegisterServiceBuilderTest
                = new DescriptionReceiptRegisterServiceBuilderTest();
        receiptRegisterService = new ReceiptRegisterServiceImplTest();
        productAndDiscountService = new ProductAndDiscountServiceImplTest();
    }

    @Test
    @GetMapping("/info")
    @DisplayName("get instruction if web instruction exists")
    public void getInstruction(){
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
                "-> Map<ProductTest, Integer> <br></h3>";
        webInstruction += "<h3>receipt/ --- update receipt, PUT, request body " +
                "-> Map<ProductTest, Integer> <br></h3>";
        webInstruction += "<h3>receipt/ --- delete product from receipt, DELETE, request body " +
                "-> Map<ProductTest, Integer> <br></h3>";

        Assertions.assertThat(webInstruction).isNotEmpty();
    }

    @Nested
    class ParameterizedTests{
        @ParameterizedTest
        @MethodSource("getCurrentArgumentsFromDataBase")
        @DisplayName("get current arguments from DB")
        public void saveProductToReceipt(String id, String quantity, String discountCardNumber
                , Map<Integer, Integer> products, DiscountCardTest testDiscountCardTest){

            receiptRegisterService.createFullReceipt();
            receiptRegisterService.getReceipt().setProducts(new HashMap<>());


            if (id != null && !id.isEmpty()){
                if (quantity == null){
                    quantity = "1";
                }

                receiptRegisterService
                        .addProductToReceiptByID(Integer.parseInt(id), Integer.parseInt(quantity));
            }

            DiscountCardTest discountCardTest = null;

            if (discountCardNumber != null){
                try {
                    discountCardTest = productAndDiscountService
                            .getDiscountCardFromDataBaseByCardNumber(Integer.parseInt(discountCardNumber));
                }catch (InvalidDiscountCardExceptionTest e){
                    discountCardTest = null;
                }

                if (discountCardTest != null){
                    receiptRegisterService.addDiscountCardToReceipt(discountCardTest);
                }
            }


            Assertions.assertThat(receiptRegisterService.getReceipt().getProducts())
                    .isEqualTo(products);

            if (discountCardTest == null){
                Assertions.assertThat(discountCardTest).isEqualTo(testDiscountCardTest);
            }else{
                Assertions.assertThat(discountCardTest.getNumberCard())
                        .isEqualTo(testDiscountCardTest.getNumberCard());
            }

        }

        @ParameterizedTest
        @MethodSource("throwExceptionIfIncorrectArguments")
        @DisplayName("throw ID or DISCOUNT or QUANTITY EXCEPTION if incorrect parameters")
        public void throwExceptionIfIncorrectArguments(String id, String quantity
                , String discountCardNumber){

            receiptRegisterService.createFullReceipt();
            receiptRegisterService.getReceipt().setProducts(new HashMap<>());

            if (id != null && !id.isEmpty()){
                if (quantity == null){
                    quantity = "1";
                }
                try {
                    receiptRegisterService
                            .addProductToReceiptByID(Integer.parseInt(id), Integer.parseInt(quantity));
                }catch (Exception e){
                    Assertions.assertThat(e).isInstanceOfAny(InvalidIDExceptionTest.class
                            , InvalidQuantityExceptionTest.class);
                }
            }else{
                Assertions.assertThat(true).isTrue();
            }


            DiscountCardTest discountCardTest = null;

            if (discountCardNumber != null){
                try {
                    discountCardTest = productAndDiscountService
                            .getDiscountCardFromDataBaseByCardNumber(Integer.parseInt(discountCardNumber));

                    if (discountCardTest != null){
                        receiptRegisterService.addDiscountCardToReceipt(discountCardTest);
                    }

                    Assertions.assertThat(false).isTrue();

                }catch (Exception e){
                    Assertions.assertThat(e).isInstanceOf(InvalidDiscountCardExceptionTest.class);
                }
            }
        }

        @GetMapping("/delete")
        @ParameterizedTest
        @CsvSource({
                "1,",
                "4,",
                "-1,",
                ","
        })
        @DisplayName("throw ID or QUANTITY EXCEPTION if incorrect parameters")
        public void deleteProductFromReceiptByID(String id, String quantity){
            if (id != null && !id.isEmpty()){
                if (quantity == null){
                    quantity = "1";
                }

                try {
                    receiptRegisterService
                            .deleteProductFromReceiptByID(Integer.parseInt(id), Integer.parseInt(quantity));
                    Assertions.assertThat(false).isTrue();
                }catch (Exception e){
                    Assertions.assertThat(e).isInstanceOfAny(InvalidIDExceptionTest.class
                            , InvalidQuantityExceptionTest.class);
                }
            }else{
                Assertions.assertThat(true).isTrue();
            }
        }

        static Stream<Arguments> getCurrentArgumentsFromDataBase(){
            DiscountCardTest discountCardTest
                    = new DiscountCardTest(1234, 6, true);
            return Stream.of(
                    Arguments.of("1", "5", "12345", new HashMap<>(){{put(1,5);}}, null),
                    Arguments.of("1", "5", "1234", new HashMap<>(){{put(1,5);}}, discountCardTest),
                    Arguments.of("1", "5", null, new HashMap<>(){{put(1,5);}}, null),
                    Arguments.of("1", null,"1234", new HashMap<>(){{put(1,1);}}, discountCardTest),
                    Arguments.of(null, "1", "1234", new HashMap<>(), discountCardTest),
                    Arguments.of(null, "1", null, new HashMap<>(), null)
            );
        }

        static Stream<Arguments> throwExceptionIfIncorrectArguments(){
            DiscountCardTest discountCardTest = new DiscountCardTest(1234, 6, true);
            return Stream.of(
                Arguments.of("1", "-1", "-12345"),
                Arguments.of("-1", "1", "12345")
            );
        }

    }


    @Test
    @GetMapping("/")
    @DisplayName("get receipt if is not empty")
    public void getReceipt(){
        String receiptInfo = receiptRegisterService.createFullReceipt();

        String webReceiptInfo = receiptInfo.replaceFirst("~", "<pre>")
                .replaceFirst("/~", "</pre>");

        Assertions.assertThat(webReceiptInfo).isNotEmpty();
    }

    @Test
    @GetMapping("/download")
    @DisplayName("download receipt if is not empty")
    public void downloadReceipt(){
        String file = receiptRegisterService.createFullReceipt().
                replaceFirst("~", "").replaceFirst("/~", "");

        Assertions.assertThat(file).isNotEmpty();
    }

    @Test
    @GetMapping("/db")
    @DisplayName("create default receipt from DB if products from DB is not empty")
    public void createDefaultReceiptFromDataBase(){
        ReceiptRegisterServiceBuilderTest receiptRegisterServiceBuilder = new ReceiptRegisterServiceBuilderTest();

        descriptionReceiptRegisterServiceBuilderTest
                .initializeReceiptRegisterServiceBuilder(receiptRegisterServiceBuilder);

        receiptRegisterService
                = receiptRegisterServiceBuilder.getReceiptRegisterServiceWithParameters();

        Assertions.assertThat(receiptRegisterService.getReceipt().getProducts()).isNotEmpty();
    }
}
