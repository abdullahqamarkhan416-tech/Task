import java.util.Arrays;
import java.util.Random;

public class SelectionSort {

    public static void sort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[min]) min = j;
            }
            int t = a[i]; a[i] = a[min]; a[min] = t;
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
        System.out.println("Selection sort of 10000 elements: "
                + (System.nanoTime() - start) / 1_000_000.0 + " ms");
    }
}
