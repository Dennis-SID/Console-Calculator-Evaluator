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

    /**
     * Turns String type expression to the list of separate symbols in it
     * @param expression String representation of math formula
     * @return list (sequence) of symbols with a String type
     */
    private static List<String> stringToList(String expression) {
        return new ArrayList<String>(Arrays.asList(expression.split("")));
    }

    /**
     * gets expression and removes unneeded spaces in it
     * @param expression assumes that expression has unnecessary spaces
     * @return list without spaces (sequence of numbers and operators) for further math processing
     */
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

    /**
     * return boolean value (true / false) depends is String symbol is represents number or not
     * @param number String representation of number
     * @return boolean value (true or false) whether the symbol is number or any other symbol
     */
    private static boolean isNumber (String number) {
        List<String> numberSequence = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
        return numberSequence.contains(number);
    }

    /**
     * TODO need tests
     * @param expression
     * @return
     */
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

    /**
     * performs multiplication
     * @param expression assumes expression has no exponentiation, or all exponentiation operation
     *                   were already performed
     * @return String list with multiplication math operations performed
     */
    private static List<String> multiplication(List<String> expression) {
       Stack<String> resultExpression = new Stack<>();

       for (String op : expression) {
           if (op.equals("*")) {
               String op1 = resultExpression.pop();
               String op2 = resultExpression.pop();
               double result = mathOperation(op1, op, op2);
               resultExpression.push(String.valueOf(result));
           } else {
               resultExpression.push(op);
           }
       }
        return new ArrayList<>(resultExpression);
    }

    /**
     * final calculation operation, assumes that expression already has not exponentiation,
     * multiplication, subtraction and other high priority operators.
     * @param expression assumes that expression has only addition or subtraction operators
     * @return result double value
     */
    private static double additionSubtraction(List<String> expression) {
        Stack<String> resultExpression = new Stack<>();

        for (String op : expression) {
            if (op.equals("+") || op.equals("-")) {
                String op1 = resultExpression.pop();
                String op2 = resultExpression.pop();
                double result = mathOperation(op1, op, op2);
                resultExpression.push(String.valueOf(result));
            } else {
                resultExpression.push(op);
            }
        }
        return Double.parseDouble(resultExpression.pop());
    }

    /**
     * implement math operation
     * @param operand1 first operand of math expression
     * @param operator operators from expression
     * @param operand2 second operand math expression
     * @return double value as a result of math operation
     * @throws ArithmeticException
     */
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
