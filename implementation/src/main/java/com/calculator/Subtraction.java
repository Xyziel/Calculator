package com.calculator;

import com.calculator.classData.OperationData;

import java.util.List;

public class Subtraction implements IOperation {

    private OperationData data;

    public Subtraction() {
        data = new OperationData("-", 1, 2);
    }

    @Override
    public double compute(List<Double> listOfNumbers) {
        if(listOfNumbers.size() == 1) {
            return -listOfNumbers.get(0);
        }
        else {
            return listOfNumbers.get(1) - listOfNumbers.get(0);
        }
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
    public void displayInfo() {
        System.out.print(data.getOperator() + " - " + getClass().getName() + " allows for calculate the subtraction of 2 numbers and the negation of a number. Example: 4-1=3, -(-2)=2");
    }
}

