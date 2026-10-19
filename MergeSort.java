import java.util.Arrays;
import java.util.Random;

public class MergeSort {

    public static void sort(int[] a) {
        if (a.length < 2) return;
        sort(a, new int[a.length], 0, a.length - 1);
    }

    private static void sort(int[] a, int[] tmp, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        sort(a, tmp, lo, mid);
        sort(a, tmp, mid + 1, hi);
        merge(a, tmp, lo, mid, hi);
    }

    private static void merge(int[] a, int[] tmp, int lo, int mid, int hi) {
        int i = lo, j = mid + 1, k = lo;
        while (i <= mid && j <= hi) {
            tmp[k++] = (a[i] <= a[j]) ? a[i++] : a[j++];
        }
        while (i <= mid) tmp[k++] = a[i++];
        while (j <= hi) tmp[k++] = a[j++];
        System.arraycopy(tmp, lo, a, lo, hi - lo + 1);
    }

    public static void main(String[] args) {
        int[] small = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Before: " + Arrays.toString(small));
        sort(small);
        System.out.println("After : " + Arrays.toString(small));

        int[] big = new Random(42).ints(10000, 0, 1000000).toArray();
        long start = System.nanoTime();
        sort(big);
        System.out.println("Merge sort of 10000 elements: "
                + (System.nanoTime() - start) / 1_000_000.0 + " ms");
    }
}
