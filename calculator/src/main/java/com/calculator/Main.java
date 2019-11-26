package com.calculator;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {

    public static void main(String[] args) {
        File dir = new File("C:\\Users\\Dom\\Documents\\GitHub\\Calc\\plugins");
        File fileDir = new File("C:\\Users\\Dom\\Documents\\GitHub\\Calc\\plugins\\com\\calculator");
        String[] files = fileDir.list();

        Calc calc = new Calc();

        if (files.length != 0) {
            try {
                URL loadPath = dir.toURI().toURL();
                URL[] classUrl = new URL[]{loadPath};
                ClassLoader cl = new URLClassLoader(classUrl);
                System.out.print("Załadowane pluginy: ");
                for (String file : files) {
                    System.out.print(file + ", ");
                    Class loadedClass = cl.loadClass("com.calculator." + file.replace(".class", ""));
                    Object obj = loadedClass.newInstance();
                    IPlugins plugin = (IPlugins) obj;
                    calc.setAvailableOperators(plugin.getOperator());
                    calc.setOperatorWeightMap(plugin.getOperator(), plugin.getWeightOfOperator());
                    calc.setFunctionMap(plugin.getOperator(), plugin);
                }

                calc.calculate("2-2");

            } catch (MalformedURLException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
