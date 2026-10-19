import java.util.Arrays;
import java.util.Random;

public class Main {

    // Change this value: 1000, 10000, 50000
    // Or run: java SortBenchmark 10000
    static int SIZE = 1000;

    static void bubbleSort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j]; a[j] = a[j + 1]; a[j + 1] = t;
                    swapped = true;
                }
            }
            if (!swapped) break;
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

    static void mergeSort(int[] a, int l, int r) {
        if (l >= r) return;
        int m = (l + r) / 2;
        mergeSort(a, l, m);
        mergeSort(a, m + 1, r);
        merge(a, l, m, r);
    }

    static void merge(int[] a, int l, int m, int r) {
        int[] left = Arrays.copyOfRange(a, l, m + 1);
        int[] right = Arrays.copyOfRange(a, m + 1, r + 1);
        int i = 0, j = 0, k = l;
        while (i < left.length && j < right.length) {
            a[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        }
        while (i < left.length) a[k++] = left[i++];
        while (j < right.length) a[k++] = right[j++];
    }

    static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) if (a[i - 1] > a[i]) return false;
        return true;
    }

    static void time(String name, int[] original, Runnable unused, int which) {
        int[] a = original.clone();
        long start = System.nanoTime();
        switch (which) {
            case 0: bubbleSort(a); break;
            case 1: selectionSort(a); break;
            case 2: insertionSort(a); break;
            case 3: mergeSort(a, 0, a.length - 1); break;
        }
        long end = System.nanoTime();
        System.out.printf("%-15s %10.3f ms   sorted=%b%n", name, (end - start) / 1_000_000.0, isSorted(a));
    }

    public static void main(String[] args) {
        if (args.length > 0) SIZE = Integer.parseInt(args[0]);

        int[] data = new int[SIZE];
        Random rnd = new Random(42);
        for (int i = 0; i < SIZE; i++) data[i] = rnd.nextInt(1_000_000);

        System.out.println("Array size: " + SIZE);
        time("Bubble Sort", data, null, 0);
        time("Selection Sort", data, null, 1);
        time("Insertion Sort", data, null, 2);
        time("Merge Sort", data, null, 3);
    }
}
