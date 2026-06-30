package service;

/**
 * Provides matrix arithmetic operations (business logic layer).
 */
public class MatrixService {

    /**
     * Adds two matrices of the same dimensions.
     *
     * @param matrix1 the first matrix
     * @param matrix2 the second matrix
     * @return the element-wise sum of the two matrices
     */
    public int[][] additionMatrix(int[][] matrix1, int[][] matrix2) {
        int rows = matrix1.length;
        int cols = matrix1[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    /**
     * Subtracts two matrices of the same dimensions.
     *
     * @param matrix1 the first matrix
     * @param matrix2 the second matrix
     * @return the element-wise difference of the two matrices
     */
    public int[][] subtractionMatrix(int[][] matrix1, int[][] matrix2) {
        int rows = matrix1.length;
        int cols = matrix1[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return result;
    }

    /**
     * Multiplies two matrices (columns of matrix1 must equal rows of matrix2).
     *
     * @param matrix1 the first matrix
     * @param matrix2 the second matrix
     * @return the matrix product of the two matrices
     */
    public int[][] multiplicationMatrix(int[][] matrix1, int[][] matrix2) {
        int rows = matrix1.length;
        int cols = matrix2[0].length;
        int common = matrix1[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < common; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }
}
