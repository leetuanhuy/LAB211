package utility;

import java.util.Scanner;

/**
 * Utility class providing static methods for validating user input. Supports
 * validation for integers, doubles, strings, and dates with appropriate error
 * handling and retry logic.
 */
public class Validation {

    private Validation() {
    }

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
}
