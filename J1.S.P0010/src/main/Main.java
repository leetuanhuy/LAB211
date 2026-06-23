package main;

import constant.InputConstant;
import java.util.Arrays;
import algorithm.LinearSearch;
import utils.ArrayUtils;
import utils.InputValidator;

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

        int size = InputValidator.getInt(
                "Enter number of array: ",
                "Out of range! Please enter a positive integer.",
                "Wrong format! You must input an integer number.",
                InputConstant.MIN_VALUE,
                InputConstant.MAX_VALUE);
        int value = InputValidator.getInt(
                "Enter number to search: ",
                "Out of range! Please enter a positive integer.",
                "Wrong format! You must input an integer number.",
                InputConstant.MIN_VALUE,
                InputConstant.MAX_VALUE);

        int[] array = ArrayUtils.generateArray(size, size);
        System.out.println("The arrays: " + Arrays.toString(array));

        int[] indices = LinearSearch.findAllIndices(value, array);
        if (indices.length == 0) {
            System.out.println("Not found " + value);
        } else {
            System.out.println("Found " + value + " at indices: " + Arrays.toString(indices));
        }
    }

}
