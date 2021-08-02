package com.dsidelnik;

import java.util.Objects;

public class CalculatorLogic {

    private double operand1;
    private double operand2;
    private String operator;

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public double getOperand1() {
        return operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public String getOperator() {
        return operator;
    }

    public double mathOperation(double operand1, String operator, double operand2) throws ArithmeticException {
        if (operator.equals("/") && operand2 == 0) throw new ArithmeticException();

        switch(operator) {
            case("+") : return operand1 + operand2;
            case("-") : return operand1 - operand2;
            case("X") : return operand1 * operand2;
            case("/") : return operand1 / operand2;
            case("%") : return operand1 * (operand2 / 100);
        }
        return 0.0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalculatorLogic that = (CalculatorLogic) o;
        return Double.compare(that.operand1, operand1) == 0 && Double.compare(that.operand2, operand2) == 0 && Objects.equals(operator, that.operator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand1, operand2, operator);
    }
}
