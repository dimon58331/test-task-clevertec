package com.clevertec.test.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SmartFileScanner{
    private final List<String> linesFromFile;

    public SmartFileScanner(String fileDirectory) throws IOException {

        Scanner scanner = new Scanner(Paths.get(fileDirectory));
        linesFromFile = new ArrayList<>();

        scanner.useDelimiter(System.getProperty("line.separator"));

        while(scanner.hasNext()){
            String line = scanner.nextLine();
            if (!line.contains("#")){
                linesFromFile.add(line);
            }
        }
        scanner.close();
    }

    public static void saveToFile(String object, String fileName){
        String s = System.getProperty("user.home");
        s += "\\desktop\\";
        try(FileWriter writer = new FileWriter(s + fileName, false)) {
            writer.write(object);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private List<String> getParts(String regex){
        List<String> parts = new ArrayList<>();

        for (String line : linesFromFile){
            if (!line.isEmpty()) {
                parts.addAll(Arrays.asList(line.split(regex)));
            }
        }

        return parts;
    }

    public List<List<Number>> getNumberParameters(String firstRegex, String secondRegex){

        List<List<Number>> allParameters = new ArrayList<>();

        for (String line : getParts(firstRegex)){
            String[] parts = line.split(secondRegex);

            List<Number> numberList = new ArrayList<>();

            for (String str : parts){
                if (isInteger(str)){
                    numberList.add(Integer.parseInt(str));
                }else if (isDouble(str)){
                    numberList.add(Double.parseDouble(str));
                }
            }

            if (!numberList.isEmpty()){
                allParameters.add(numberList);
            }
        }

        return allParameters;
    }

    public List<String> getStringParameters(String firstRegex, String secondRegex){

        List<String> allParameters = new ArrayList<>();

        for (String line : getParts(firstRegex)){
            String[] parts = line.split(secondRegex);

            for (String str : parts){
                if (!isInteger(str) && !isDouble(str)){
                    allParameters.add(str);
                }
            }
        }

        return allParameters;
    }

    private boolean isInteger(String str){
        try {
            int a = Integer.parseInt(str);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private boolean isDouble(String str){
        try {
            double a = Double.parseDouble(str);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
