# Circular Queue & Sorting Algorithms (Java)

## Files
- `CircularQueue.java`: enqueue, dequeue, front, isEmpty, isFull, display
- `SortBenchmark.java`: Bubble, Selection, Insertion, Merge sort with timing

## Run
```
javac *.java
java CircularQueue
java SortBenchmark 1000
java SortBenchmark 10000
java SortBenchmark 50000
```

## Results (time in ms, measured on one run; your numbers will differ by device)
| Algorithm | 1,000 | 10,000 | 50,000 |
|-----------|-------|--------|--------|
| Bubble    | 9.003 | 321.204 | 3311.951 |
| Selection | 10.600 | 184.287 | 732.381 |
| Insertion | 7.850 | 26.738 | 358.184 |
| Merge     | 0.646 | 4.062 | 14.794 |

**Observations:** Merge Sort is the fastest at every size, and at 50,000 it is about 220x faster than Bubble Sort. Bubble Sort is the slowest and grows the fastest. Insertion Sort is the best of the O(n²) sorts on random data.

## Comparison

| Algorithm | Time (best / avg / worst) | Space | Stable |
|-----------|---------------------------|-------|--------|
| Bubble    | O(n) / O(n²) / O(n²)      | O(1)  | Yes |
| Selection | O(n²) / O(n²) / O(n²)     | O(1)  | No  |
| Insertion | O(n) / O(n²) / O(n²)      | O(1)  | Yes |
| Merge     | O(n log n) all cases      | O(n)  | Yes |

### Bubble Sort
- **Advantages:** very simple; detects an already sorted array (with swap flag); stable.
- **Disadvantages:** very slow on large data; many swaps.
- **Use when:** teaching, or tiny/nearly sorted data.

### Selection Sort
- **Advantages:** simple; makes at most n-1 swaps.
- **Disadvantages:** always O(n²), even if already sorted; not stable.
- **Use when:** writes/swaps are expensive (e.g., flash memory) and the data is small.

### Insertion Sort
- **Advantages:** fast on small or nearly sorted data; stable; works online (sorts as data arrives).
- **Disadvantages:** O(n²) on large random data.
- **Use when:** small arrays, nearly sorted data, or as a helper inside hybrid sorts.

### Merge Sort
- **Advantages:** guaranteed O(n log n); stable; great for large data and linked lists.
- **Disadvantages:** needs O(n) extra memory; slower than simple sorts on tiny arrays.
- **Use when:** large datasets, when stability matters, or when worst-case time must be guaranteed.

## Conclusion
For large inputs (10,000 to 50,000), Merge Sort is by far the fastest. Among the O(n²) sorts, Insertion is usually fastest, Selection is in the middle, and Bubble is usually slowest.

## Push to GitHub
```
git init
git add .
git commit -m "Circular queue and sorting algorithms"
git branch -M main
git remote add origin https://github.com/<your-username>/<repo-name>.git
git push -u origin main
```
