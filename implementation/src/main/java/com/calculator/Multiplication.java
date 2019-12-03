package com.calculator;

import com.calculator.classData.OperationData;

import java.util.List;

public class Multiplication implements IOperation {

    private OperationData data;

    public Multiplication() {
        data = new OperationData("*", 2, 2);
    }

    @Override
    public String getOperator() {
        return null;
    }

    @Override
    public int getWeightOfOperator() {
        return 0;
    }

    @Override
    public int getNumberOfOperands() {
        return data.getNumberOfOperands();
    }

    @Override
    public double compute(List<Double> listOfNumbers) {
        return listOfNumbers.get(1) * listOfNumbers.get(0);
    }

    @Override
    public void displayInfo() {
        System.out.print(data.getOperator() + " - " + getClass().getName() + " allows for calculate the multiplication of 2 numbers. Example: 2*3=6");
    }
}
