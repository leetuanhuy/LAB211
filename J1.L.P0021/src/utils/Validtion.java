/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Validtion {

    private static final Scanner sc = new Scanner(System.in);

    /**
     * Get integer input from user with validation
     *
     * @param msg display message
     * @param errRange error message if out of range
     * @param errFormat error message if wrong format
     * @param min minimum value
     * @param max maximum value
     * @return valid integer within correct input
     */
    public static int getInt(String msg, String errRange, String errFormat,
            int min, int max) {
        while (true) {
            System.out.printf(msg);
            try {
                int n = Integer.parseInt(sc.nextLine());
                if (n >= min && n <= max) {
                    return n;
                } else {
                    System.out.println(errRange);
                }
            } catch (NumberFormatException ex) {
                System.out.println(errFormat);
            }
        }

    }

    /**
     * Get double input from user with validation
     *
     * @param msg display message
     * @param errRange error message if out of range
     * @param errFormat error message if wrong format
     * @param min minimum value
     * @param max maximum value
     * @return valid double within correct input
     */
    public static double getDouble(String msg, String errRange, String errFormat,
            double min, double max) {
        while (true) {
            System.out.printf(msg);
            try {
                double n = Double.parseDouble(sc.nextLine());
                if (n >= min && n <= max) {
                    return n;
                } else {
                    System.out.println(errRange);
                }
            } catch (NumberFormatException ex) {
                System.out.println(errFormat);
            }
        }

    }

    /**
     * Get string input from user with validation
     *
     * @param msg display message
     * @param err error message if input is empty
     * @return Non-empty string
     */
    public static String getString(String msg, String err) {
        while (true) {
            System.out.printf(msg);
            String s = sc.nextLine();
            if (s.isEmpty()) {
                System.err.println(err);
                continue;
            }
            return s;
        }
    }

    /**
     * Get date input from user with format validation Format
     * required:dd-MM-yyyy(example: 11-Apr-2009)
     *
     * @param msg display message
     * @param errEmpty error message if empty
     * @param errFormat error message if format invalid
     * @param errInvalid error message if date invalid
     * @return valid date string in dd-MM-yyyy format
     */
    public static String getDate(String msg, String errEmpty, String errFormat,
            String errInvalid) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        dateFormat.setLenient(false);
        while (true) {
            System.out.printf(msg);
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println(errEmpty);
                continue;
            }
            if (!input.matches("\\d{1,2}-[A-Za-z]{3}-\\d{4}")) {
                System.err.println(errFormat);
                continue;
            }
            try {
                dateFormat.parse(input);
                return input;
            } catch (ParseException e) {
                System.out.println(errInvalid);
            }

        }

    }
 

    public static boolean getYN(String msg) {
        while (true) {
            String input = getString(msg, "Input cannot be empty.");
            if (input.equalsIgnoreCase("Y")) {
                return true;
            }
            if (input.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Please enter Y or N.");
        }
    }

    public static String getUD(String msg) {
        while (true) {
            String input = getString(msg, "Input cannot be empty.");
            if (input.equalsIgnoreCase("U")) {
                return "U";
            }
            if (input.equalsIgnoreCase("D")) {
                return "D";
            }
            System.out.println("Please enter U or D.");
        }
    }
}
