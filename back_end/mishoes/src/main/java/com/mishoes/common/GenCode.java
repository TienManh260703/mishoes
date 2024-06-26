package com.mishoes.common;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Random;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenCode {
    static int NUMBER_LENGTH = 10;

    static String USER = "UO";
    static String BRAND = "B0";
    static String CART = "C0";
    static String CATEGORY = "CT0";
    static String COLOR = "CL0";
    static String MATERIAL = "M0";
    static String ORDER = "OD0";
    static String PRODUCT = "P";

    public static String generateUSER() {
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, NUMBER_LENGTH));
        String formattedNumber = String.format("%0" + NUMBER_LENGTH + "d", randomNumber);
        return USER + formattedNumber;
    }

    public static String generatePRODUCT() {
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, NUMBER_LENGTH));
        String formattedNumber = String.format("%0" + NUMBER_LENGTH + "d", randomNumber);
        return PRODUCT + formattedNumber;
    }
    public static String generateBRAND (){
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, NUMBER_LENGTH));
        String formattedNumber = String.format("%0" + NUMBER_LENGTH + "d", randomNumber);
        return BRAND + formattedNumber;
    }

    public static String generateCATEGORY (){
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, NUMBER_LENGTH));
        String formattedNumber = String.format("%0" + NUMBER_LENGTH + "d", randomNumber);
        return CATEGORY + formattedNumber;
    }
    public static String generateORDER (){
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, NUMBER_LENGTH));
        String formattedNumber = String.format("%0" + NUMBER_LENGTH + "d", randomNumber);
        return ORDER + formattedNumber;
    }
}
