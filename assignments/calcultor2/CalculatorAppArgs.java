package com.corejava.calculatorAppArgs;

public class CalculatorAppArgs {

    public static void main(String[] args) {

        float numberOne= Float.valueOf(args[0]);

        float numberTwo = Float.valueOf(args[2]);

        char operation = args[1].charAt(0);

        switch(operation) {

            case '+': System.out.println(numberOne+numberTwo);
                    break;
            case '-': System.out.println(numberOne-numberTwo);

                    break;
            case '*': System.out.println(numberOne*numberTwo);

                    break;
            case '/': System.out.println(numberOne/numberTwo);

                    break;
            default: System.out.println("Enter a valid choice");

                    break;

        }    

    }

 

}

 
