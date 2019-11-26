package com.calculator;

public class Subtraction implements IPlugins {


    @Override
    public double compute(double a, double b) {
        return a - b;
    }

    @Override
    public String getOperator() {
        return null;
    }

    @Override
    public int getWeightOfOperator() {
        return 0;
    }
}
