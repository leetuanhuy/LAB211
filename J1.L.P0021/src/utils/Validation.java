package utils;


import java.util.Scanner;

public final class Validation {

    private static final Scanner SC = new Scanner(System.in);

    private Validation() {
    }

    /**
     * Gets an integer input within range.
     *
     * @param msg       prompt message
     * @param errRange  error if out of range
     * @param errFormat error if not a number
     * @param min       minimum value
     * @param max       maximum value
     * @return valid integer
     */
    public static int getInt(String msg, String errRange, String errFormat,
            int min, int max) {
        while (true) {
            System.out.printf(msg);
            try {
                int value = Integer.parseInt(SC.nextLine());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println(errRange);
            } catch (NumberFormatException ex) {
                System.out.println(errFormat);
            }
        }
    }

    /**
     * Gets a non-empty string input.
     *
     * @param msg prompt message
     * @param err error if input is empty
     * @return non-empty string
     */
    public static String getString(String msg, String err) {
        while (true) {
            System.out.printf(msg);
            String input = SC.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.err.println(err);
        }
    }

    /**
     * Prompts the user for a Yes/No (Y/N) confirmation.
     *
     * @param msg           the prompt message displayed to the user
     * @param errEmpty      the error message displayed when the input is empty
     * @param invalidChoice the error message displayed when the input is
     *                      neither 'Y' nor 'N' (case-insensitive)
     * @return {@code true} if the user enters 'Y' or 'y'; {@code false} if 'N'
     * or 'n'
     */
    public static boolean confirmYesNo(String msg, String errEmpty, String invalidChoice) {
        while (true) {
            String input = getString(msg, errEmpty);
            if (input.equalsIgnoreCase("Y")) {
                return true;
            }
            if (input.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println(invalidChoice);
        }
    }

    /**
     * Prompts the user to choose between Update (U) or Delete (D).
     *
     * @param msg           the prompt message displayed to the user
     * @param errEmpty      the error message displayed when the input is empty
     * @param invalidChoice the error message displayed when the input is
     *                      neither 'U' nor 'D' (case-insensitive)
     * @return "U" if the user chooses update, "D" if the user chooses delete
     */
    public static String getUpdateOrDeleteChoice(String msg, String errEmpty, String invalidChoice) {
        while (true) {
            String input = getString(msg, errEmpty);
            if (input.equalsIgnoreCase("U")) {
                return "U";
            }
            if (input.equalsIgnoreCase("D")) {
                return "D";
            }
            System.out.println(invalidChoice);
        }
    }

}
