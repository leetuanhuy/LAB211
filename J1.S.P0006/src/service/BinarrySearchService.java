/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.Random;

/**
 *
 * @author Admin
 */
public class BinarrySearchService {

    /**
     *Sort array using bubble sort algorithm
     * 
     * @param a array to sort
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
/**
 * Generate array with random integer
 * @param n size of array
 * @param max maximum value 
 * @return  array filled with random value
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
 * 
 * @param a sorted array 
 * @param x value to find
 * @param left left boundary
 * @param right right boundary
 * @return return index of value , if not found return -1
 */
    public static int binarySearch(int[] a, int x, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] == x) {
                return mid;
            }
            if (a[mid] < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
