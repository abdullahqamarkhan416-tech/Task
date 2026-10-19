import java.util.Arrays;
import java.util.Random;

public class BubbleSort {

    public static void sort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j]; a[j] = a[j + 1]; a[j + 1] = t;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void main(String[] args) {
        int[] small = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Before: " + Arrays.toString(small));
        sort(small);
        System.out.println("After : " + Arrays.toString(small));

        int[] big = new Random(42).ints(10000, 0, 1000000).toArray();
        long start = System.nanoTime();
        sort(big);
        System.out.println("Bubble sort of 10000 elements: "
                + (System.nanoTime() - start) / 1_000_000.0 + " ms");
    }
}
