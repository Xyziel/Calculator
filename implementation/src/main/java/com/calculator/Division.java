package com.calculator;

public class Division implements IPlugins {

    private String operator = "/";
    private int weightOfOperator = 2;

    @Override
    public double compute(double a, double b) {
        return a / b;
    }

    @Override
    public String getOperator() {
        return operator;
    }

    @Override
    public int getWeightOfOperator() {
        return weightOfOperator;
    }
}