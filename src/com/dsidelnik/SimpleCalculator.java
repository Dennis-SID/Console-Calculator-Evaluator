package com.dsidelnik;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Simplest console calculator
 * @author youtName
 * @version 1.0
 */
public class SimpleCalculator {

    public static void main (String [] args) {
        String expression = "10 + 20 + 33";
        System.out.println(stringToList(expression));
        System.out.println(spaceRemover(stringToList(expression)));
        System.out.println(multiNumber(spaceRemover(stringToList(expression))));
    }

    private static List<String> lineParser(String expression) {
        return new ArrayList<String>(Arrays.asList(expression.split("")));
    }

    private static List<String> stringToList(String expression) {
        return new ArrayList<String>(Arrays.asList(expression.split("")));
    }

    private static List<String> spaceRemover(List<String> expression) {
        return new ArrayList<String>(expression.stream()
                                               .filter(x -> !x.equals(" "))
                                               .collect(Collectors.toList()));
    }

    private static List<String> multiNumber(List<String> expression) {
        List<String> resultList = new ArrayList<>();
        String number = "";
        for (int i = 0; i < expression.size(); ) {
            while (isNumber(expression.get(i)) && i < expression.size() - 1) {
                number += expression.get(i);
                i++;
            }

            if (!number.equals("")) {
                resultList.add(number);
                number = "";
            }

            if (!isNumber(expression.get(i))) {
                resultList.add(expression.get(i));
                i++;
            }
        }
        resultList.add(expression.get(expression.size() - 1));
        return resultList;
    }


    private static boolean isNumber (String number) {
        List<String> numberSequence = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
        return numberSequence.contains(number);
    }
}
