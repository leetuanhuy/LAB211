/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.BMIStatus;
import model.Operator;

/**
 *
 * @author Admin
 */
public class Calculator {

    /**
     * This method to calculate on two number
     *
     * @param a First operand
     * @param operator the operator
     * @param b Second operand
     * @return the calculated result
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
            case DIVDE:
                if (b == 0) {
                    throw new ArithmeticException("Can not devide by 0");
                }
                return a / b;
            case EXPONENTS:
                return Math.pow(a, b);
            default:
                return 0;
        }

    }

    /**
     * This method to calculate BMI body
     *
     * @param weight weight is kilogram
     * @param height height is meters
     * @return
     */
    public static BMIStatus calculateBMI(double weight, double height) {
        double heightMeters = height / 100.0;
        double bmi = weight / (heightMeters * heightMeters);
        if (bmi < 19) {
            return BMIStatus.UNDER_STANDARD;
        } else if (bmi <= 25) {
            return BMIStatus.STANDARD;
        } else if (bmi <= 30) {
            return BMIStatus.OVERWEIGHT;
        } else if (bmi <= 40) {
            return BMIStatus.FAT;
        } else {
            return BMIStatus.VERRY_FAT;
        }
    }

    /**
     * Validates and convert a string to an operator enum
     *
     * @param operator string of operator
     * @return operator enum , if valid return null ;
     */
    public static Operator checkOperator(String operator) {
        try {
            return switch (operator.trim()) {
                case "+" ->
                    Operator.ADD;
                case "-" ->
                    Operator.SUBTRACT;
                case "*" ->
                    Operator.MULTIPLY;
                case "/" ->
                    Operator.DIVDE;
                case "^" ->
                    Operator.EXPONENTS;
                case "=" ->
                    Operator.EQUAL;
                default ->
                    null;
            };
        } catch (NullPointerException e) {
            return null;
        }

    }
}
