package utils;

import static constant.AppConstant.VALID_COURSES;
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
     * Asks Y/N and returns true for Y, false for N.
     *
     * @param msg prompt message
     * @return true if Y, false if N
     */
    public static boolean confirmYesNo(String msg) {
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

    /**
     * Asks U/D and returns the choice.
     *
     * @param msg prompt message
     * @return "U" or "D"
     */
    public static String getUpdateOrDeleteChoice(String msg) {
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
