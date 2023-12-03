package com.krylov.assignment.utilities;

public class PriceParser {
    public static Float priceParser(String rawPrice){
        StringBuilder trimmedString= new StringBuilder();
        for(int i =0; i<rawPrice.length(); i++){
            char currentEl = rawPrice.charAt(i);
            if(Character.isDigit(currentEl) || currentEl=='.'){
                trimmedString.append(currentEl);
            }
        }
        if(trimmedString.length()>0){
            return Float.parseFloat(trimmedString.toString());
        } else {
            throw new IllegalArgumentException();
        }
    }
}
