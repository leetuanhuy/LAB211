/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Validation {

    public static final Scanner sc = new Scanner(System.in);

    /**
     *
     * @param msg display message
     * @param min min value
     * @param max max value
     * @return
     */
    public static int getInt(String msg, int min, int max) {
        while (true) {
            System.out.println(msg);
            try {
                int n = Integer.parseInt(sc.nextLine());
                if (n >= min && n <= max) {
                    return n;
                } else {                   
                    System.out.println("Out of range number" + " " + min + "to " + max);
                }
            } catch (NumberFormatException ex) {
                System.err.println("Wrong format input, please enter the integer number");
            }
        }
    }

}
