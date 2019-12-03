package com.calculator;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;

        try {
            fh = new FileHandler("C:\\Users\\Dom\\Documents\\GitHub\\Calculator\\logs.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException | IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

        File dir = new File("C:\\Users\\Dom\\Documents\\GitHub\\Calculator\\plugins");
        File fileDir = new File("C:\\Users\\Dom\\Documents\\GitHub\\Calculator\\plugins\\com\\calculator");
        String[] files = fileDir.list();

        Calc calc = Calc.getInstance();

        String expression;

        System.out.println("Available operations:");
        System.out.println("Basic:");
        System.out.println("+ - Addition\n- - Subtraction\n* - Multiplication\n/ - Division\n() - Parentheses");

        if (files != null && files.length != 0) {
            try {
                URL loadPath = dir.toURI().toURL();
                URL[] classUrl = new URL[]{loadPath};
                ClassLoader cl = new URLClassLoader(classUrl);

                System.out.print("\nLoaded plugins: ");

                for (String file : files) {
                    System.out.println();

                    Class loadedClass = cl.loadClass("com.calculator." + file.replace(".class", ""));
                    Object obj = loadedClass.newInstance();
                    IOperation operation = (IOperation) obj;

                    calc.setAvailableOperators(operation.getOperator());
                    calc.setOperatorWeightMap(operation.getOperator(),operation.getWeightOfOperator());
                    calc.setFunctionMap(operation.getOperator(), operation);
                    calc.setNumberOfOperandsMap(operation.getOperator(), operation.getNumberOfOperands());

                    operation.displayInfo();
                }
            } catch (MalformedURLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }

        System.out.println("\n\nEnter 'exit' to exit application.");

        while(true) {

            System.out.println("\nEnter the expression to calculate:");

            expression = scanner.nextLine();

            if (expression.equals("exit")) {
                break;
            }

            try {
                System.out.println("Result: " + calc.calculate(expression));
            } catch (NumberFormatException | EmptyStackException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid expression");
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }
}
