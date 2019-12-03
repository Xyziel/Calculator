package com.calculator.classData;

import java.util.*;

public class CalcData {

    private HashMap<String, Object> functionMap;
    private HashMap<String, Integer> operatorWeightMap;
    private HashMap<String, Integer> numberOfOperandsMap;
    private ArrayList<String> availableOperators;

    public CalcData(HashMap<String, Object> functionMap, HashMap<String, Integer> operatorWeightMap,
                    ArrayList<String> availableOperators, HashMap<String, Integer> numberOfOperandsMap) {

        this.functionMap = functionMap;
        this.operatorWeightMap = operatorWeightMap;
        this.availableOperators = availableOperators;
        this.numberOfOperandsMap = numberOfOperandsMap;
    }

    public Map<String, Object> getFunctionMap() {
        return functionMap;
    }

    public Map<String, Integer> getOperatorWeightMap() {
        return operatorWeightMap;
    }

    public ArrayList<String> getAvailableOperators() {
        return availableOperators;
    }

    public HashMap<String, Integer> getNumberOfOperandsMap() {
        return numberOfOperandsMap;
    }
}
