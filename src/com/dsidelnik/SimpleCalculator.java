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
        String expression = "10 + 20 + 333";
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
        for (int i = 0; i < expression.size() - 1; ) {
            while (isNumber(expression.get(i)) && i < expression.size()) {
                number += expression.get(i);
                i++;
                if (i == expression.size()) break;
            }

            if (!number.equals("")) {
                resultList.add(number);
                number = "";
            }

            if (i == expression.size()) break;

            if (!isNumber(expression.get(i))) {
                resultList.add(expression.get(i));
                i++;
            }
        }
        return resultList;
    }


    private static boolean isNumber (String number) {
        List<String> numberSequence = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
        return numberSequence.contains(number);
    }

    private static List<String> exponentiation (List<String> expression) {
        Stack<String> resultExp = new Stack<>();
        for (int i = 0; i < expression.size(); i++) {
            if (expression.get(i).equals("^")) {
                String op1 = resultExp.pop();
                String op2 = expression.get(i + 1);
                double result = mathOperation(op1, expression.get(i), op2);
                resultExp.push(String.valueOf(result));
                i++;
            } else {
                resultExp.push(expression.get(i));
            }
        }
        return new ArrayList<String>(resultExp);
    }

    private static double mathOperation (String operand1, String operator, String operand2) throws ArithmeticException {
        double op1 = Double.parseDouble(operand1);
        double op2 = Double.parseDouble(operand2);
        if (operator.equals("/") && op2 == 0.0) throw new ArithmeticException();
        switch (operator) {
            case ("+") : return op1 + op2;
            case ("-") : return op1 - op2;
            case ("*") : return op1 * op2;
            case ("/") : return op1 / op2;
            case ("^") : Math.pow(op1, op2);
         }
         return 0.0;
    }
}
