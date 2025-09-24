package Algorithms;


import java.util.Random;


public class QuickSort {
    private static final int INSERTION_CUTOFF = 16;
    private static final Random RAND = new Random();


    public static <T extends Comparable<T>> void sort(T[] array, Metrics m) {
        sort(array, 0, array.length - 1, m);
    }

    private static <T extends Comparable<T>> void sort(T[] a, int lo, int hi, Metrics m) {
        int head = lo, tail = hi;
        while (head < tail) {
            if (m != null) m.enter();
            try {
                int n = tail - head + 1;
                if (n <= INSERTION_CUTOFF) {
                    insertionSort(a, head, tail + 1, m);
                    return;
                }
                int pivotIndex = head + RAND.nextInt(n);
                T pivot = a[pivotIndex];
                swap(a, pivotIndex, tail);
                int store = partition(a, head, tail, pivot, m);
                int leftSize = store - head;
                int rightSize = tail - store;
                if (leftSize < rightSize) {
                    sort(a, head, store - 1, m);
                    head = store + 1;
                } else {
                    sort(a, store + 1, tail, m);
                    tail = store - 1;
                }
            } finally {
                if (m != null) m.exit();
            }
        }
    }private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi, T pivot, Metrics m) {
        int store = lo;
        for (int i = lo; i < hi; i++) {
            if (m != null) m.comp();
            if (a[i].compareTo(pivot) < 0) {
                swap(a, i, store++);
            }
        }
        swap(a, store, hi);
        return store;
    }


    private static <T extends Comparable<T>> void insertionSort(T[] a, int lo, int hi, Metrics m) {
        for (int i = lo + 1; i < hi; i++) {
            for (int j = i; j > lo && a[j].compareTo(a[j - 1]) < 0; j--) {
                if (m != null) m.comp();
                swap(a, j, j - 1);
            }
        }
    }


    private static <T> void swap(T[] a, int i, int j) {
        T tmp = a[i]; a[i] = a[j]; a[j] = tmp;
    }
}

