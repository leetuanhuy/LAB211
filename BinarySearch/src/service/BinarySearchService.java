/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.Random;

/**
 * Service class for binary search operations
 *
 * @author Admin
 */
public class BinarySearchService {

    private static final int NOT_FOUND = -1;

    /**
     * Generate array with random integers
     *
     * @param n Size of array
     * @param max Maximum value (inclusive)
     * @return Array filled with random values
     */
    public static int[] getRandomArray(int n, int max) {
        int[] a = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(max + 1);
        }
        return a;
    }

    /**
     * Display array elements to console
     *
     * @param a Array to display
     */
    public static void displayArray(int[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if (i < a.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /**
     * Perform binary search on sorted array
     *
     * @param a Sorted array to search in
     * @param x Value to find
     * @param left Left boundary
     * @param right Right boundary
     * @return Index of value if found, -1 otherwise
     */
    public static int binarySearch(int[] a, int x, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] == x) {
                return mid;
            }
            if (a[mid] > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Sort array using bubble sort algorithm
     *
     * @param a Array to sort (modifies in place)
     */
    public static void sortArray(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    public static int linearSearch(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) {
                return i;
            } 
        }
        return -1;
    }
}
