package utils;


import java.util.Scanner;

/**
 * Utility class providing static methods for validating user input.
 * Supports validation for integers, doubles, strings, and dates
 * with appropriate error handling and retry logic.
 */
public class Validation {
    private Validation(){}

    public static final Scanner sc = new Scanner(System.in);

    /**
     * Prompts user for an integer within a specified range.
     * Repeats until a valid input is provided.
     *
     * @param msg            the prompt message to display
     * @param msgErrorRange  the error message when input is out of range
     * @param msgWrongFormat the error message when input is not a number
     * @param min            the minimum allowed value (inclusive)
     * @param max            the maximum allowed value (inclusive)
     * @return a valid integer within the specified range
     */
    public static int getInt(String msg, String msgErrorRange, String msgWrongFormat, int min, int max) {
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
     * Prompts user for a non-empty string.
     * Repeats until a valid input is provided.
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
            System.err.println(err);
        }
    }

    /**
     * Prompts user for a string that may be empty. 
     *
     * @param msg the prompt message to display
     * @return the trimmed input string (may be empty)
     */
    public static String getOptionalString(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    /**
     * Prompts user for an optional integer. If the user presses Enter,
     * returns the defaultValue. Otherwise validates range and format,
     * repeating until a valid value is entered.
     *
     * @param msg            the prompt message to display
     * @param msgErrorRange  the error message when input is out of range
     * @param msgWrongFormat the error message when input is not a number
     * @param min            the minimum allowed value (inclusive)
     * @param max            the maximum allowed value (inclusive)
     * @param defaultValue   the value to return if input is empty
     * @return a valid integer or the defaultValue if input was empty
     */
    public static int getOptionalInt(String msg, String msgErrorRange, String msgWrongFormat, int min, int max, int defaultValue) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                return defaultValue;
            }
            try {
                int n = Integer.parseInt(input);
                if (min <= n && n <= max) {
                    return n;
                }
                System.err.println(msgErrorRange);
            } catch (NumberFormatException ex) {
                System.err.println(msgWrongFormat);
            }
        }
    }
}
