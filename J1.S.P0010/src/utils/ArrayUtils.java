/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Random;

/**
 *
 * @author Admin
 */
public class ArrayUtils {

    /**
     *
     * @param n size of array
     * @param max Maximum values
     * @return array filed random number
     */
    public static int[] generateArray(int n, int max) {
        int[] a = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(max + 1);
        }
        return a;
    }
}
