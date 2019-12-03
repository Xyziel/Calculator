package com.calculator;

import com.calculator.classData.OperationData;

import java.util.List;

public class Division implements IOperation {

    private OperationData data;

    public Division() {
        data = new OperationData("/", 2, 2);
    }

    @Override
    public double compute(List<Double> listOfNumbers) throws ArithmeticException {
        if (listOfNumbers.get(0) == 0) {
            System.out.println("You cannot divide by 0!");
        }
        return listOfNumbers.get(1) / listOfNumbers.get(0);
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
    public void displayInfo() {
        System.out.print(data.getOperator() + " - " + getClass().getName() + " allows for calculate the division of 2 numbers. Example: 4/2=2");
    }
}