package view;

import constants.InputConstants;
import enums.BMIStatus;
import enums.Operator;
import service.Calculator;
import utils.Formatter;
import utils.Validation;

/**
 * Main class for calculator application with normal and BMI calculation features
 */
public class Main {

    /**
     * Main entry point for the calculator application
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {     
        while (true) {
            displayMenu();
            int choice = Validation.getInt("Select option (1-3): ", 
                    InputConstants.MIN_CHOICE, InputConstants.MAX_CHOICE);
            switch (choice) {
                case 1 -> normalCalculator();
                case 2 -> bmiCalculator();
                default -> {
                    System.out.println("Exit program. Goodbye!");
                    System.exit(0);
                }
                    
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
        double memory = Validation.getDouble("Enter number: ",
                InputConstants.MIN_NUMBER, InputConstants.MAX_NUMBER);
        
        while (true) {
            String opStr = Validation.getString("Enter Operator: ", "Operator cannot be empty");
            Operator operator = Calculator.checkOperator(opStr);
            
            if (operator == null) {
                System.out.println("Please input (+, -, *, /, ^)");
                continue;
            }
            
            if (operator == Operator.EQUAL) {
                System.out.println("Result:" + Formatter.formatNumber(memory));
                return;
            }
            
            double number = Validation.getDouble("Enter number: ",
                    InputConstants.MIN_NUMBER, InputConstants.MAX_NUMBER);
            
            try {
                memory = Calculator.calculate(memory, operator, number);
                System.out.println("Memory:" +Formatter.formatNumber(memory));
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
        double weight = Validation.getDouble("Enter weight (kg): ", 
                InputConstants.MIN_WEIGHT, InputConstants.MAX_WEIGHT);
        double height = Validation.getDouble("Enter height (cm): ",
                InputConstants.MIN_HEIGHT, InputConstants.MAX_HEIGHT);
        
        double bmi = Calculator.getBMINumber(weight, height);
        BMIStatus status = Calculator.calculateBMI(weight, height);
        System.out.printf("BMI number: %.2f%n", bmi);
        System.out.println("BMI Status: " + status.getDescription());
    }

    
   
}
