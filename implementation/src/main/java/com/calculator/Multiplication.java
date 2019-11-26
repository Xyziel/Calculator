package com.calculator;

public class Multiplication implements IPlugins {

    private String operator = "*";
    private int weightOfOperator = 2;

    @Override
    public String getOperator() {
        return operator;
    }

    @Override
    public int getWeightOfOperator() {
        return weightOfOperator;
    }

    @Override
    public double compute(double a, double b) {
        return a * b;
    }
}
