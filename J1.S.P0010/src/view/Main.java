/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;

import constant.InputConstants;
import java.util.Arrays;
import utils.Validation;

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

        int n = Validation.getInt(
                "Enter number of array: ",
                "Out of range! Please enter a positive integer.",
                "Wrong format! You must input an integer number.",
                InputConstants.MIN_VALUE,
                InputConstants.MAX_VALUE);
        int x = Validation.getInt(
                "Enter number to search: ",
                "Out of range! Please enter a positive integer.",
                "Wrong format! You must input an integer number.",
                InputConstants.MIN_VALUE,
                InputConstants.MAX_VALUE);
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
