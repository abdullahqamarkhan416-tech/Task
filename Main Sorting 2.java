import java.util.Arrays;
import java.util.Random;

public class Main {

    // ---------- Algorithms ----------

    static void bubbleSort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j]; a[j] = a[j + 1]; a[j + 1] = t;
                    swapped = true;
                }
            }
            if (!swapped) break; // already sorted
        }
    }

    static void selectionSort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[min]) min = j;
            }
            int t = a[i]; a[i] = a[min]; a[min] = t;
        }
    }

    static void insertionSort(int[] a) {
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

    static void mergeSort(int[] a) {
        if (a.length < 2) return;
        mergeSort(a, new int[a.length], 0, a.length - 1);
    }

    private static void mergeSort(int[] a, int[] tmp, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) >>> 1;
        mergeSort(a, tmp, lo, mid);
        mergeSort(a, tmp, mid + 1, hi);
        int i = lo, j = mid + 1, k = lo;
        while (i <= mid && j <= hi) tmp[k++] = (a[i] <= a[j]) ? a[i++] : a[j++];
        while (i <= mid) tmp[k++] = a[i++];
        while (j <= hi) tmp[k++] = a[j++];
        System.arraycopy(tmp, lo, a, lo, hi - lo + 1);
    }

    private static final Random PIVOT_RNG = new Random(7);

    static void quickSort(int[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(int[] a, int lo, int hi) {
        while (lo < hi) {
            // random pivot avoids worst case on sorted input
            int p = lo + PIVOT_RNG.nextInt(hi - lo + 1);
            int pivot = a[p];
            a[p] = a[hi]; a[hi] = pivot;
            int i = lo;
            for (int j = lo; j < hi; j++) {
                if (a[j] < pivot) {
                    int t = a[i]; a[i] = a[j]; a[j] = t;
                    i++;
                }
            }
            a[hi] = a[i]; a[i] = pivot;
            // recurse on smaller side, loop on larger (limits stack depth)
            if (i - lo < hi - i) {
                quickSort(a, lo, i - 1);
                lo = i + 1;
            } else {
                quickSort(a, i + 1, hi);
                hi = i - 1;
            }
        }
    }

    // ---------- Benchmark ----------

    interface Sorter { void sort(int[] a); }

    static double timeMs(Sorter s, int[] original) {
        int[] copy = Arrays.copyOf(original, original.length);
        long start = System.nanoTime();
        s.sort(copy);
        long end = System.nanoTime();
        int[] expected = Arrays.copyOf(original, original.length);
        Arrays.sort(expected);
        if (!Arrays.equals(copy, expected)) {
            throw new IllegalStateException("Sort produced wrong result!");
        }
        return (end - start) / 1_000_000.0;
    }

    public static void main(String[] args) {
        int[] sizes = {1_000, 10_000, 50_000, 100_000};
        if (args.length > 0) {
            sizes = Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
        }

        String[] names = {"Bubble", "Selection", "Insertion", "Merge", "Quick"};
        Sorter[] sorters = {
            Main::bubbleSort,
            Main::selectionSort,
            Main::insertionSort,
            Main::mergeSort,
            Main::quickSort
        };

        // JIT warm-up so the first timing isn't unfairly slow
        int[] warm = new Random(1).ints(2000, 0, 100_000).toArray();
        for (Sorter s : sorters) timeMs(s, warm);

        System.out.printf("%-10s", "Size");
        for (String n : names) System.out.printf("%14s", n + " (ms)");
        System.out.println();

        for (int size : sizes) {
            int[] data = new Random(42).ints(size, 0, 1_000_000).toArray();
            System.out.printf("%-10d", size);
            for (Sorter s : sorters) {
                System.out.printf("%14.2f", timeMs(s, data));
            }
            System.out.println();
        }
    }
}
