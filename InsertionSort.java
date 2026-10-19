import java.util.Arrays;
import java.util.Random;

public class InsertionSort {

    public static void sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
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
        System.out.println("Insertion sort of 10000 elements: "
                + (System.nanoTime() - start) / 1_000_000.0 + " ms");
    }
}
