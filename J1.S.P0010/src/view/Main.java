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
     *
     * @param args
     */
    public static void main(String[] args) {

        int n = utils.Validation.getInt("Enter number of array: ", 1, Integer.MAX_VALUE);
        int x = utils.Validation.getInt("Enter number to search: ", 1, Integer.MAX_VALUE);
        service.LinearSearch linearSearch = new service.LinearSearch();
        int[] a = utils.ArrayUtils.generateArray(n, n);
//        int a[] = {1,2,3,3,4,5,5,3};
        System.out.println("The arrays: " + Arrays.toString(a));
        int[] indices = linearSearch.search(x, a);
        if (indices.length == 0) {
            System.out.println("Not found " + x);
        } else {
            System.out.println("Found " + x + " at indices: " + Arrays.toString(indices));
        }
    }

}
