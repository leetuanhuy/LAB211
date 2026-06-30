package controller;

import constant.MatrixConstant;
import service.MatrixService;
import utility.Validation;

/**
 * Handles user interaction flow: input, validation, calls service.
 */
public class MatrixController {

    private final MatrixService matrixService;

    /** Constructor injection: service is provided from outside. */
    public MatrixController(MatrixService matrixService) {
        this.matrixService = matrixService;
    }

    /**
     * Prompts the user to input a matrix.
     *
     * @param name the display name of the matrix (e.g. "Matrix 1")
     * @return the input matrix
     */
    private int[][] inputMatrix(String name) {
        int rows = Validation.getInt("Enter Row " + name + ": ",
                "Values of matrix must be the number",
                "Values of matrix must be the number",
                MatrixConstant.MIN_DIMENSION, MatrixConstant.MAX_DIMENSION);
        int cols = Validation.getInt("Enter Column " + name + ": ",
                "Values of matrix must be the number",
                "Values of matrix must be the number",
                MatrixConstant.MIN_DIMENSION, MatrixConstant.MAX_DIMENSION);
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Validation.getInt(
                        "Enter " + name + "[" + (i + 1) + "][" + (j + 1) + "]: ",
                        "Values of matrix must be the number",
                        "Values of matrix must be the number",
                        Integer.MIN_VALUE, Integer.MAX_VALUE);
            }
        }
        return matrix;
    }

    /**
     * Prints a matrix to the console in the format [val1][val2]...
     *
     * @param matrix the matrix to display
     */
    private void displayMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print("[" + val + "]");
            }
            System.out.println();
        }
    }

    /**
     * Handles the addition flow: prompts user for two matrices, validates that
     * they have the same dimensions, computes the sum, and displays the result.
     */
    public void add() {
        System.out.println("--------Addition--------");
        int[][] matrix1 = inputMatrix("Matrix 1");
        int[][] matrix2 = inputMatrix("Matrix 2");
        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
            System.out.println("Two matrices must have same number of rows and columns");
            return;
        }
        System.out.println("--------Result--------");
        displayMatrix(matrix1);
        System.out.println("+");
        displayMatrix(matrix2);
        System.out.println("=");
        displayMatrix(matrixService.additionMatrix(matrix1, matrix2));
    }

    /**
     * Handles the subtraction flow: prompts user for two matrices, validates that
     * they have the same dimensions, computes the difference, and displays the result.
     */
    public void sub() {
        System.out.println("--------Subtraction--------");
        int[][] matrix1 = inputMatrix("Matrix 1");
        int[][] matrix2 = inputMatrix("Matrix 2");
        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
            System.out.println("Two matrices must have same number of rows and columns");
            return;
        }
        System.out.println("--------Result--------");
        displayMatrix(matrix1);
        System.out.println("-");
        displayMatrix(matrix2);
        System.out.println("=");
        displayMatrix(matrixService.subtractionMatrix(matrix1, matrix2));
    }

    /**
     * Handles the multiplication flow: prompts user for two matrices, validates that
     * the number of columns of matrix1 equals the number of rows of matrix2,
     * computes the product, and displays the result.
     */
    public void mul() {
        System.out.println("--------Multiplication--------");
        int[][] matrix1 = inputMatrix("Matrix 1");
        int[][] matrix2 = inputMatrix("Matrix 2");
        if (matrix1[0].length != matrix2.length) {
            System.out.println("Number of columns of Matrix 1 must equal number of rows of Matrix 2");
            return;
        }
        System.out.println("--------Result--------");
        displayMatrix(matrix1);
        System.out.println("x");
        displayMatrix(matrix2);
        System.out.println("=");
        displayMatrix(matrixService.multiplicationMatrix(matrix1, matrix2));
    }
}
