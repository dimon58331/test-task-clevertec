package com.clevertec.test.utility;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class SmartFileScannerTest {
    @Test
    void getNumberParametersIsExactlyNumber() throws IOException{
        SmartFileScanner smartFileScanner
                = new SmartFileScanner("src\\main\\resources\\id_quantity.txt");
        List<List<Number>> numberParametersList = smartFileScanner.getNumberParameters("\\s+", "-");

        List<Object> numberList = new ArrayList<>();
        for (var numberParameters : numberParametersList){
            numberList.addAll(numberParameters);
        }
        Assertions.assertThat(numberList).hasOnlyElementsOfType(Number.class);
    }

    @Test
    void getStringParametersIsExactlyString() throws IOException{
        SmartFileScanner smartFileScanner
                = new SmartFileScanner("src\\main\\resources\\id_quantity.txt");
        List<String> stringParametersList = smartFileScanner.getStringParameters("\\s+", "-");

        Assertions.assertThat(stringParametersList).hasOnlyElementsOfType(String.class);
    }

    @Test
    void throwExceptionIfFileIsEmptyOrIncorrect(){
        Assertions.assertThatThrownBy
                (() -> new SmartFileScanner("src\\main\\resources\\id_quantity_1.txt"));
    }
}
