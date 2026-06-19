package utility;

import java.util.Random;

/**
 *
 * @author Admin
 */
public class ArrayUtils {

    private ArrayUtils() {
    }

    /**
     *
     * @param n size of array
     * @param max Maximum values
     * @return array filed random number
     */
    public static int[] generateArray(int n, int max) {
        int[] a = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(max + 1);
        }
        return a;
    }
}
