package com.calculator;

public class Main {

    public static void main(String[] args) {
        ICal cal = new Cal();
        System.out.println(cal.calculate("2/2*2.2"));
    }
}
