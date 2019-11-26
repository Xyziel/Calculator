package com.calculator;

public class Addition implements IPlugins {

    @Override
    public String getOperator() {
        return null;
    }

    @Override
    public int getWeightOfOperator() {
        return 0;
    }

    @Override
    public double compute(double a, double b) {
        return a + b;
    }
}
