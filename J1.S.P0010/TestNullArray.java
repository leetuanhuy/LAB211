public class TestNullArray {
    public static void main(String[] args) {
        // Test case 1: Normal case - Works fine
        System.out.println("=== Test 1: Normal case ===");
        int[] array = {1, 2, 3, 4, 5};
        int result = service.LinearSearchService.linearSearch(3, array);
        System.out.println("Result: " + result);  // Output: 2
        
        // Test case 2: NULL ARRAY - Will crash!
        System.out.println("\n=== Test 2: NULL ARRAY ===");
        int[] nullArray = null;
        try {
            int result2 = service.LinearSearchService.linearSearch(5, nullArray);
            System.out.println("Result: " + result2);
        } catch (NullPointerException ex) {
            System.out.println("ERROR: NullPointerException caught!");
            System.out.println("Message: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        // Test case 3: Empty array
        System.out.println("\n=== Test 3: Empty array ===");
        int[] emptyArray = {};
        int result3 = service.LinearSearchService.linearSearch(5, emptyArray);
        System.out.println("Result: " + result3);  // Output: -1
    }
}
