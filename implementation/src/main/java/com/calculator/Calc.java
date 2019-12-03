package com.calculator;

import com.calculator.classData.CalcData;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Calc implements ICal{

    private static final Calc instance = new Calc();
    private CalcData data;

    private Calc () {
        data = new CalcData(new HashMap<String, Object>(){{
            put("+", new Addition());
            put("-", new Subtraction());
            put("/", new Division());
            put("*", new Multiplication());
        }},
                new HashMap<String, Integer>(){{
                    put("+", 1);
                    put("-", 1);
                    put("/", 2);
                    put("*", 2);
                    put("(", 0);
                    put(")", 0);
                }},
                new ArrayList<String>() {{
                    add("+");
                    add("-");
                    add("/");
                    add("*");
                    add("(");
                    add(")");
                }},
                new HashMap<String, Integer>(){{
                    put("+", 2);
                    put("-", 2);
                    put("/", 2);
                    put("*", 2);
                }}
        );
    }

    @Override
    public double calculate(String input) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Stack<String> operator = new Stack<>();
        List<String> number = new ArrayList<>();
        List<Integer> flagsOfNegation = new ArrayList<>();

        String exp = input;
        exp = exp.replace(',', '.');

        for (int i = 0; i < data.getAvailableOperators().size(); i++) { //availableOperators.size()
            if (input.contains(data.getAvailableOperators().get(i))) { //availableOperators.get(i)
                exp = exp.replace((data.getAvailableOperators().get(i)), " " + data.getAvailableOperators().get(i) + " ");
            }
        }

        exp = exp.replace("  ", " ");
        String[] elements = exp.split(" ");
        List<String> elementsList = Arrays.asList(elements);
        int counter = 0;
        for(String elem: elementsList) {
            if (!elem.equals("")) {
                if (!isOperator(elem)) {
                    number.add(elem);
                }

                else if (elem.contains("(")) {
                    operator.push(elem);
                }

                else if (elem.contains(")")) {
                    while (!operator.isEmpty() && !operator.peek().contains("(")) {
                        number.add(operator.pop());
                    }
                    if (!operator.isEmpty() && !operator.peek().contains("(")) {
                        throw new NumberFormatException("Invalid expression");
                    }
                    else {
                        operator.pop();
                    }
                }
                else {

                    if (elem.equals("-") && (counter == 0 || elementsList.get(counter - 1).equals("("))) {
                        flagsOfNegation.add(counter);
                    }

                    while (!operator.isEmpty() && (data.getOperatorWeightMap().get(elem) <= data.getOperatorWeightMap().get(operator.peek()))) {
                        if (operator.peek().contains("(")) {
                            throw new NumberFormatException("Invalid expression");
                        }
                        number.add(operator.pop());
                    }
                    operator.push(elem);
                }
                counter++;
            }
        }

        while (!operator.isEmpty()) {
            if (operator.peek().contains("(")) {
                throw new NumberFormatException("Invalid expression");
            }
            number.add(operator.pop());
        }

        for (int i = 0; i < number.size(); i++) {
            if (isOperator(number.get(i))) {

                int numberOfOperands = data.getNumberOfOperandsMap().get(number.get(i));
                List<Double> numbersToOperate = new ArrayList<>();

                if(number.get(i).equals("-") && flagsOfNegation.size() != 0 && i != flagsOfNegation.get(0)) {
                    numberOfOperands = 1;
                    flagsOfNegation = flagsOfNegation.subList(1, flagsOfNegation.size());
                }

                for(int l = 0; l < numberOfOperands; l++) {
                    numbersToOperate.add(Double.parseDouble(number.get(i - 1 - l)));
                }

                number.add(i + 1, data.getFunctionMap().get(number.get(i)).getClass().getMethod("compute", List.class).invoke(data.getFunctionMap().get(number.get(i)), numbersToOperate).toString());

                for (int j = 0; j < numberOfOperands + 1; j++) {
                    number.remove(i - numberOfOperands);
                }
                i = 0;
            }
        }
        if (number.size() > 1) {
            throw new NumberFormatException("Invalid expression");
        }
        return Double.parseDouble(number.get(0));
    }

    public void setFunctionMap(String sign , Object operation) {
        data.getFunctionMap().put(sign, operation);
    }

    public void setAvailableOperators(String sign) {
        data.getAvailableOperators().add(sign);
    }

    public void setOperatorWeightMap(String sign, Integer weight) {
        data.getOperatorWeightMap().put(sign, weight);
    }

    public void setNumberOfOperandsMap(String sign, Integer numberOfOperands) {
        data.getNumberOfOperandsMap().put(sign, numberOfOperands);
    }

    private boolean isOperator(String element) {
        for(String operator : data.getAvailableOperators()) {
            if(operator.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public static Calc getInstance() {
        return instance;
    }
}
