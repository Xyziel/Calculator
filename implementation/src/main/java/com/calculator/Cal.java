package com.calculator;

import java.util.ArrayList;
import java.util.List;

public class Cal implements ICal{

    @Override
    public double calculate(String expression) {

        List<Double> doubleNumbers = new ArrayList<>();
        String ops = getOperators(expression.replaceAll("[/*]", ""));

        String[] numbersToParse = expression.split("[-+]");

        for(String exp: numbersToParse) {
            if (exp.equals("")) {
                doubleNumbers.add(0.0);
            }
            else if (exp.contains("*") || exp.contains("/")) {
                List<Double> dNumbers = new ArrayList<>();
                String[] numbers = exp.split("[*/]");
                String operators = getOperators(exp);
                for (String number: numbers) {
                    dNumbers.add(Double.parseDouble(number));
                }
                doubleNumbers.add(getResult(dNumbers, operators));
            }
            else {
                doubleNumbers.add(Double.parseDouble(exp));
            }
        }

        return getResult(doubleNumbers, ops);
    }

    private double getResult(List<Double> doubleNumbers, String ops) {
        double result = doubleNumbers.get(0);
        for (Double number: doubleNumbers.subList(1, doubleNumbers.size())) {
            switch (ops.charAt(0)) {
                case '+':
                    result = add(result, number);
                    break;
                case '-':
                    result = subtract(result, number);
                    break;
                case '*':
                    result = multiply(result, number);
                    break;
                case '/':
                    result = divide(result, number);
                    break;
                default:
                    break;
            }
            if (ops.length() != 1) {
                ops = ops.substring(1);
            }

        }
        return result;
    }

    private String getOperators(String exp) {
        String operators = "";
        for (int i = 0; i < exp.length(); i++) {
            switch (exp.charAt(i)) {
                case '+':
                    operators = operators.concat("+");
                    break;
                case '-':
                    operators = operators.concat("-");
                    break;
                case '*':
                    operators = operators.concat("*");
                    break;
                case '/':
                    operators = operators.concat("/");
                    break;
                default:
                    break;
            }
        }
        return operators;
    }

    @Override
    public double add(double v, double v1) {
        return v + v1;
    }

    @Override
    public double subtract(double v, double v1) {
        return v - v1;
    }

    @Override
    public double multiply(double v, double v1) {
        return v * v1;
    }

    @Override
    public double divide(double v, double v1) {
        return v / v1;
    }
}
