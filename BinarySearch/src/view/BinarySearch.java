/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;

import java.util.Arrays;

/**
 * Main class for Binary Search application
 * @author Admin
 */
public class BinarySearch {

    /**
     * Entry point of the application
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        int n = utils.Validation.getInt("Enter number of elements in Array: ", 1, Integer.MAX_VALUE);
        int x = utils.Validation.getInt("Enter value to search: ", 1, Integer.MAX_VALUE);
        int[] a = service.BinarySearchService.getRandomArray(n, n);
        service.BinarySearchService.sortArray(a);
        System.out.println("Array: " + Arrays.toString(a));
        
        int index = service.BinarySearchService.binarySearch(a, x, 0, n - 1);
        if (index == -1) {
            System.out.println("Not found the value " + x + " in array");
        } else {
            System.out.println("Found " + x + " at index: " + index);
        }
        
//        int linearIndex = service.BinarySearchService.linearSearch(a, x);
//        if (linearIndex == -1) {
//            System.out.println("Not found the value " + x + " in array");
//        } else {
//            System.out.println("Found " + x + " at index " + linearIndex);
//        }
    }

}
