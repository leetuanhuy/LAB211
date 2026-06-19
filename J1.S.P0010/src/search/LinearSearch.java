package search;

/**
 *
 * @author Admin
 */
public class LinearSearch {

    private LinearSearch() {
    }

    /**
     * Find all indices of value in array
     *
     * @param value value to find
     * @param array array to search
     * @return indices where value is found
     */
    public static int[] findAllIndices(int value, int[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                count++;
            }
        }

        int[] indices = new int[count];
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                indices[index++] = i;
            }
        }
        return indices;
    }
}
