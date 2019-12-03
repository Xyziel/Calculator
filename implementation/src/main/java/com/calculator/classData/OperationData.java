package com.calculator.classData;

public class OperationData {

    private String operator;
    private int weightOfOperator;
    private int numberOfOperands;

    public OperationData(String operator, int weightOfOperator, int numberOfOperands) {
        this.operator = operator;
        this.weightOfOperator = weightOfOperator;
        this.numberOfOperands = numberOfOperands;
    }

    public String getOperator() {
        return operator;
    }

    public int getWeightOfOperator() {
        return weightOfOperator;
    }

    public int getNumberOfOperands() {
        return numberOfOperands;
    }
}