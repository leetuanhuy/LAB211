/*
 * Class InputUntil - Support user input
 */
package Untils;

import java.util.Scanner;

/**
 * @author Admin
 */
public class InputUntil {
    private static Scanner scanner = new Scanner(System.in);

    // Input string
    public static String inputString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Input integer
    public static int inputInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Please enter a valid integer!");
            }
        }
    }

    // Input double
    public static double inputDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Please enter a valid number!");
            }
        }
    }

    // Input choice
    public static String inputChoice(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Close scanner
    public static void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
