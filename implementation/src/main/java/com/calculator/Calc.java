package com.calculator;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Calc implements ICal{

    private Map<String, Object> functionMap = new HashMap<>();
    private Map<String, Integer> operatorWeightMap = new HashMap<>();
    private String availableOperators = "+-/*()";

    public Calc () {
        functionMap.put("+", new Addition());
        functionMap.put("-", new Subtraction());
        functionMap.put("*", new Multiplication());
        functionMap.put("/", new Division());

        operatorWeightMap.put("+", 1);
        operatorWeightMap.put("-", 1);
        operatorWeightMap.put("*", 2);
        operatorWeightMap.put("/", 2);
        operatorWeightMap.put("(", -1);
        operatorWeightMap.put(")", -1);
    }

    public double calculate(String input) {
        Stack<String> operator = new Stack<>();
        List<String> number = new ArrayList<>();

        String exp = input;
        for (int i = 0; i < availableOperators.length(); i++) {
            if (input.contains(Character.toString(availableOperators.charAt(i)))) {
                exp = exp.replace(Character.toString(availableOperators.charAt(i)), " " + availableOperators.charAt(i) + " ");
            }
        }

        exp = exp.replace("  ", " ");
        System.out.println("\nWyrażenie: " + exp);
        String[] elements = exp.split(" ");
        System.out.println("Elementy wyrażenia: " + Arrays.toString(elements));

        for(String elem: elements) {
            if (!elem.equals("")) {
                if (!elem.matches("["+availableOperators.toString()+"]")) {
                    number.add(elem);
                }

                else if (elem.contains("(")) {
                    operator.push(elem);
                }

                else if (elem.contains(")")) {
                    while (!operator.isEmpty() && !operator.peek().contains("(")) {
                        number.add(operator.pop());
                    }
                    operator.pop();
                }
                else {
                    while (!operator.isEmpty() && operatorWeightMap.get(elem) <= operatorWeightMap.get(operator.peek())) {
                        number.add(operator.pop());
                    }
                    operator.push(elem);
                }
            }
        }

        while (!operator.isEmpty()) {
            number.add(operator.pop());
        }

        System.out.println("Lista z RPN: " + number);

        for (int i = 0; i < number.size(); i++) {
            if (number.get(i).matches("["+availableOperators.toString()+"]")) {
                try {
                    number.add(i + 1, functionMap.get(number.get(i)).getClass().getMethod("compute", double.class, double.class).invoke(functionMap.get(number.get(i)), Double.parseDouble(number.get(i - 2)), Double.parseDouble(number.get(i - 1))).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 3; j++) {
                    number.remove(i - 2);
                }
                i = 0;
            }
        }

        System.out.println("Wynik: " + number.get(0));

        return Double.parseDouble(number.get(0));
    }

    public void setFunctionMap(String sign , Object operation) {
        functionMap.put(sign, operation);
    }

    public void setAvailableOperators(String sign) {
        availableOperators += sign;
    }

    public void setOperatorWeightMap(String sign, Integer weight) {
        operatorWeightMap.put(sign, weight);
    }
}
