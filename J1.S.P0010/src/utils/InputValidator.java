package utils;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class InputValidator {

    private InputValidator() {
    }

    public static final Scanner sc = new Scanner(System.in);

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

}
