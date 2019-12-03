package com.calculator;

import com.calculator.classData.OperationData;

import java.util.List;

public class Addition implements IOperation {

    private OperationData data;

    public Addition() {
        data = new OperationData("+", 1, 2);
    }

    @Override
    public String getOperator() {
        return data.getOperator();
    }

    @Override
    public int getWeightOfOperator() {
        return data.getWeightOfOperator();
    }

    @Override
    public int getNumberOfOperands() {
        return data.getNumberOfOperands();
    }

    @Override
    public double compute(List<Double> listOfNumbers) {
        return listOfNumbers.get(1) + listOfNumbers.get(0);
    }

    @Override
    public void displayInfo() {
        System.out.print(data.getOperator() + " - " + getClass().getName() + " allows for calculate the addition of 2 numbers. Example: 2+3=5");
    }
}
