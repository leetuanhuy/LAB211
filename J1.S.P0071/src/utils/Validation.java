package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Utility class providing static methods for validating user input. Supports
 * validation for integers, doubles, strings, and dates with appropriate error
 * handling and retry logic.
 */
public class Validation {

    public static final Scanner sc = new Scanner(System.in);

    /**
     * Prompts user for an integer within a specified range. Repeats until a
     * valid input is provided.
     *
     * @param msg            the prompt message to display
     * @param msgErrorRange  the error message when input is out of range
     * @param msgWrongFormat the error message when input is not a number
     * @param min            the minimum allowed value (inclusive)
     * @param max            the maximum allowed value (inclusive)
     * @return a valid integer within the specified range
     */
    public static int getInt(String msg, String msgErrorRange,
            String msgWrongFormat, int min, int max) {
        while (true) {
            System.out.print(msg);
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
     * Prompts user for a double within a specified range. Repeats until a valid
     * input is provided.
     *
     * @param msg            the prompt message to display
     * @param msgErrorRange  the error message when input is out of range
     * @param msgWrongFormat the error message when input is not a number
     * @param min            the minimum allowed value (inclusive)
     * @param max            the maximum allowed value (inclusive)
     * @return a valid double within the specified range
     */
    public static double getDouble(String msg, String msgErrorRange,
            String msgWrongFormat, double min, double max) {
        while (true) {
            System.out.print(msg);
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
     * Prompts user for a non-empty string. Repeats until a valid input is
     * provided.
     *
     * @param msg the prompt message to display
     * @param err the error message when input is empty
     * @return a non-empty string
     */
    public static String getString(String msg, String err) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine();
            if (!s.isEmpty()) {
                return s;
            }
            System.out.println(err);
        }
    }

    /**
     * Prompts user for a date in dd-MM-yyyy format. Validates both the format
     * and the calendar validity of the date. Repeats until a valid date is
     * provided.
     *
     * @param msg the prompt message to display
     * @return a valid date string in dd-MM-yyyy format
     */
    public static String getDate(String msg) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);

        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.err.println("Error: Date cannot be empty!");
                continue;
            }

            if (!input.matches("\\d{2}-\\d{2}-\\d{4}")) {
                System.err.println(
                        "Error: Format must be dd-MM-yyyy (example: 15-04-2009)");
                continue;
            }

            try {
                dateFormat.parse(input);
                return input;
            } catch (ParseException e) {
                System.out.println("Error: Invalid date!");
            }
        }
    }
}
