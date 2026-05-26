/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Scanner;

/**
 * Utility class for validating user input with range checking
 * @author Admin
 */
public class Validation {

    public static final Scanner sc = new Scanner(System.in);

    /**
     * Get integer input from user with validation
     * @param msg Display message
     * @param min Minimum value
     * @param max Maximum value
     * @return Valid integer within range
     */
    public static int getInt(String msg,String msgErrorRange, String msgWrongFormat, int min, int max) {
        while (true) {
            System.out.println(msg);
            try {
                int n = Integer.parseInt(sc.nextLine());
                if (min <= n && n <= max) {
                    return n;
                }
                System.out.println(msgErrorRange);

            } catch (NumberFormatException ex) {
                System.err.println(msgWrongFormat);
            }
        }
    }

    /**
     * Get double input from user with validation
     * @param msg Display message
     * @param min Minimum value
     * @param max Maximum value
     * @return Valid double within range
     */
    public static double getDouble(String msg,String msgErrorRange, String msgWrongFormat, double min, double max) {
        while (true) {
            System.out.println(msg);
            try {
                double n = Double.parseDouble(sc.nextLine());
                if (min <= n && n <= max) {
                    return n;
                }
                System.out.println(msgErrorRange);

            } catch (NumberFormatException ex) {
                System.err.println(msgWrongFormat);
            }
        }
    }

    /**
     * Get string input from user with validation
     * @param msg Display message
     * @param err Error message if input is empty
     * @return Non-empty string
     */
    public static String getString(String msg, String err) {
        while (true) {
            System.out.println(msg);
            String s = sc.nextLine();
            if (!s.isEmpty()) {
                return s;
            }
            System.err.println(err);
        }
    }
}
