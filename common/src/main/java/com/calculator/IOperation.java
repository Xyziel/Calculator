package com.calculator;

import java.util.List;

public interface IOperation {
    double compute(List<Double> listOfNumbers);
    String getOperator();
    int getWeightOfOperator();
    int getNumberOfOperands();
    void displayInfo();
}
