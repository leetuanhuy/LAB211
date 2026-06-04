/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;



/**
 * Linear Search service
 * @author Admin
 */
public class LinearSearch {

    /**
     * Find all indices of value in array
     * @param x value to find
     * @param a array to search
     * @return indices where x is found
     */
    public int[] search(int x, int[] a) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) {
                count++;
            }
        }
        int[] indices = new int[count];
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) {
                indices[j++] = i;
            }
        }
        return indices;
    }
}
