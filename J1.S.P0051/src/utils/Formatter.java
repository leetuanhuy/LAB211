/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Admin
 */
public class Formatter {

    /**
     * Formats a number to display as integer if it's a whole number, otherwise
     * displays as double with decimal places
     *
     * @param num The number to format
     * @return Formatted string representation of the number
     */
    public static String formatNumber(double num) {
        if (num % 1 == 0) {
            // If the number is a whole number, display as integer
            return String.valueOf((long) num);
        } else {
            // Otherwise, display as double
            return String.valueOf(num);
        }
    }
}
