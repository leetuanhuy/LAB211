package service;

import enums.Operator;
import enums.BMIStatus;

/**
 * Calculator class for performing arithmetic operations and BMI calculations
 */
public class Calculator {
    
    /**
     * Performs arithmetic calculation on two numbers
     * @param a First operand
     * @param operator The operator to apply (+, -, *, /, ^)
     * @param b Second operand
     * @return The calculated result
     * @throws ArithmeticException if dividing by zero
     */
    public static double calculate(double a, Operator operator, double b) {
        switch (operator) {
            case ADD:
                return a + b;
            case SUBTRACT:
                return a - b;
            case MULTIPLY:
                return a * b;
            case DIVIDE:
                if (b == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return a / b;
            case EXPONENT:
                return Math.pow(a, b);
            default:
                return 0;
        }
    }
    
    /**
     * Calculates BMI (Body Mass Index) and returns the status
     * @param weight Weight in kilograms
     * @param height Height in centimeters
     * @return BMIStatus enum indicating the user's weight category
     */
    public static BMIStatus calculateBMI(double weight, double height) {
        // Convert height from centimeters to meters
        double heightInMeters = height / 100.0;
        double bmi = weight / (heightInMeters * heightInMeters);
        
        if (bmi < 19) {
            return BMIStatus.UNDER_STANDARD;
        } else if (bmi <= 25) {
            return BMIStatus.STANDARD;
        } else if (bmi <= 30) {
            return BMIStatus.OVERWEIGHT;
        } else if (bmi <= 40) {
            return BMIStatus.FAT;
        } else {
            return BMIStatus.VERY_FAT;
        }
    }
    
    /**
     * Validates and converts a string to an Operator enum
     * @param operator String representation of the operator (+, -, *, /, ^, =)
     * @return Operator enum if valid, null if invalid
     */
    public static Operator checkOperator(String operator) {
        try {
            switch (operator.trim()) {
                case "+": return Operator.ADD;
                case "-": return Operator.SUBTRACT;
                case "*": return Operator.MULTIPLY;
                case "/": return Operator.DIVIDE;
                case "^": return Operator.EXPONENT;
                case "=": return Operator.EQUAL;
                default: return null;
            }
        } catch (NullPointerException e) {
            return null;
        }
    }
}
    
   
  
