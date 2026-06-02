package view;

import service.Calculator;
import enums.Operator;
import enums.BMIStatus;
import utils.Validation;

/**
 * Main class for calculator application with normal and BMI calculation features
 */
public class Main {
    
    // Constants for input validation ranges
    private static final double MIN_NUMBER = -999999;
    private static final double MAX_NUMBER = 999999;
    private static final double MIN_WEIGHT = 0.1;
    private static final double MAX_WEIGHT = 500;
    private static final double MIN_HEIGHT = 50;
    private static final double MAX_HEIGHT = 250;
    private static final int MIN_CHOICE = 1;
    private static final int MAX_CHOICE = 3;

    /**
     * Main entry point for the calculator application
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        OUTER:
        while (true) {
            displayMenu();
            int choice = Validation.getInt("Select option (1-3): ", MIN_CHOICE, MAX_CHOICE);
            switch (choice) {
                case 1:
                    normalCalculator();
                    break;
                case 2:
                    bmiCalculator();
                    break;
                default:
                    System.out.println("Exit program. Goodbye!");
                    break OUTER;
            }
        }
    }

    /**
     * Displays the main menu for the calculator application
     */
    private static void displayMenu() {
        System.out.println("\n========== CALCULATOR MENU ==========");
        System.out.println("1. Normal Calculator (+, -, *, /, ^)");
        System.out.println("2. BMI Calculator");
        System.out.println("3. Exit");
        System.out.println("====================================");
    }

    /**
     * Normal calculator functionality - performs arithmetic operations
     * Supports +, -, *, /, ^ operators with continuous calculation
     */
    private static void normalCalculator() {
        System.out.println("\n----- Normal Calculator -----");
        double memory = Validation.getDouble("Enter number: ", MIN_NUMBER, MAX_NUMBER);
        
        while (true) {
            String opStr = Validation.getString("Enter Operator: ", "Operator cannot be empty");
            Operator operator = Calculator.checkOperator(opStr);
            
            if (operator == null) {
                System.out.println("Please input (+, -, *, /, ^)");
                continue;
            }
            
            if (operator == Operator.EQUAL) {
                System.out.println("Result:" + formatNumber(memory));
                return;
            }
            
            double number = Validation.getDouble("Enter number: ", MIN_NUMBER, MAX_NUMBER);
            
            try {
                memory = Calculator.calculate(memory, operator, number);
                System.out.println("Memory:" + formatNumber(memory));
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
                return;
            }
        }
    }

    /**
     * BMI calculator functionality - calculates BMI and displays health status
     * Takes weight in kg and height in cm as input
     */
    private static void bmiCalculator() {
        System.out.println("\n--- BMI Calculator ---");
        double weight = Validation.getDouble("Enter weight (kg): ", MIN_WEIGHT, MAX_WEIGHT);
        double height = Validation.getDouble("Enter height (cm): ", MIN_HEIGHT, MAX_HEIGHT);
        
        BMIStatus status = Calculator.calculateBMI(weight, height);
        System.out.println("BMI Status: " + status.getDescription());
    }

    /**
     * Formats a number to display as integer if it's a whole number,
     * otherwise displays as double with decimal places
     * @param num The number to format
     * @return Formatted string representation of the number
     */
    private static String formatNumber(double num) {
        if (num % 1 == 0) {
            // If the number is a whole number, display as integer
            return String.valueOf((long) num);
        } else {
            // Otherwise, display as double
            return String.valueOf(num);
        }
    }
}
