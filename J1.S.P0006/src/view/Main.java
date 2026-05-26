/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;

import java.util.Arrays;

/**
 *
 * @author Admin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n = utils.Validation.getInt("Enter number of array: ", 1, Integer.MAX_VALUE);
        int x = utils.Validation.getInt("Enter search value", 1, Integer.MAX_VALUE);
        int[] a = service.BinarrySearchService.getRandomArray(n, n);
        service.BinarrySearchService.sortArray(a);
        System.out.println("Sorted array: " + Arrays.toString(a));
        int index = service.BinarrySearchService.binarySearch(a, x, 0, n - 1);
        if (index == -1) {
            System.out.println("Not foud " + x + " in array");
        } else {
            System.out.println("Found " + x + " at index: " + index);
        }
    }
}




