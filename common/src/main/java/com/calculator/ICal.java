package com.calculator;

import java.lang.reflect.InvocationTargetException;

public interface ICal {
    double calculate(String var1) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

}
