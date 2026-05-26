/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;

import model.BMIStatus;
import model.Operator;
import service.Calculator;
import utils.Validation;

/**
 *
 * @author Admin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        while (true) {
            displayMenu();
            int choice = Validation.getInt("Please choice one option:", "", "Wrong format", 1, 3);
            if (choice == 1) {
                normalCalculator();
            } else if (choice == 2) {
                bmiCalculator();
            } else {
                System.out.println("Exit program, goodbye");
                break;
            }
        }

    }

    private static void displayMenu() {
        System.out.println("========== Calculator Program ==========");
        System.out.println("1. Normal Calculator");
        System.out.println("2. BMI Calculator");
        System.out.println("3. Exit");

    }

    private static void normalCalculator() {
        System.out.println("\n--Normal Calculator --");
        double memory = Validation.getDouble("Ennter number", "Number out of range", "Please enter number, try again", 0, Double.MAX_VALUE);
        while (true) {
            String opStr = utils.Validation.getString("Enter Operator", "Operator cannot empty");
            Operator operator = Calculator.checkOperator(opStr);
            if (operator == null) {
                System.out.println("Please input (+, -, *, /, ^)");
                continue;
            }
            if (operator == Operator.EQUAL) {
                System.out.println("Result: " + memory);
                return;
            }
            double number = Validation.getDouble("Ennter number", "Number out of range", "Please enter number, try again", 0, Double.MAX_VALUE);
            try {
                memory = Calculator.calculate(memory, operator, number);
                System.out.println("Memory: " + memory);
            } catch (ArithmeticException e) {
                System.out.println("Error" + e.getMessage());
            }
        }
    }

    private static void bmiCalculator() {
        System.out.println("\n----BMI Calculator ----");
        double weight = Validation.getDouble("Enter Weight", "Out of range", "BMI is digit", 0, 500);
        double height = Validation.getDouble("Enter Height(cm)", "Out of range", "BMI is digit", 0, 500);
        BMIStatus status = Calculator.calculateBMI(weight, height);
        System.out.println("BMI status: " + status.getDescription());
    }

}
