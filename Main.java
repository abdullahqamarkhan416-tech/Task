import java.util.Arrays;
import java.util.Random;

// Runs all four sorting classes and compares their time.
// Keep BubbleSort.java, SelectionSort.java, InsertionSort.java and MergeSort.java
// in the same folder as this file.
public class Main {

    interface Sorter { void sort(int[] a); }

    static double timeMs(Sorter s, int[] original) {
        int[] copy = Arrays.copyOf(original, original.length);
        long start = System.nanoTime();
        s.sort(copy);
        double ms = (System.nanoTime() - start) / 1_000_000.0;
        int[] expected = Arrays.copyOf(original, original.length);
        Arrays.sort(expected);
        if (!Arrays.equals(copy, expected)) {
            throw new IllegalStateException("Sort gave wrong result!");
        }
        return ms;
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 50000};
        String[] names = {"Bubble", "Selection", "Insertion", "Merge"};
        Sorter[] sorters = {
            BubbleSort::sort, SelectionSort::sort,
            InsertionSort::sort, MergeSort::sort
        };

        System.out.printf("%-10s", "Size");
        for (String n : names) System.out.printf("%16s", n + " (ms)");
        System.out.println();

        for (int size : sizes) {
            int[] data = new Random(42).ints(size, 0, 1000000).toArray();
            System.out.printf("%-10d", size);
            for (Sorter s : sorters) System.out.printf("%16.2f", timeMs(s, data));
            System.out.println();
        }
    }
}
