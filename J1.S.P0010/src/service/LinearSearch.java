/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class LinearSearch {

    /**
     *
     * @param x value to find
     * @param a the array
     * @return ArrayList of indices where x is found, empty if not found
     */
    public ArrayList<Integer> search(int x, int[] a) {
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) {
                indices.add(i);
            }
        }
        return indices;
    }
}
