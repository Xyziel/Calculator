package com.calculator;

public class Addition implements IPlugins {

    private String operator = "+";
    private int weightOfOperator = 1;

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
        return a + b;
    }
}
