package Algorithms;

public class MergeSort {
    private static final int INSERTION_CUTOFF = 16;


    public static <T extends Comparable<T>> void sort(T[] array, Metrics metrics) {
        @SuppressWarnings("unchecked")
        T[] buffer = (T[]) new Comparable[array.length];
        if (metrics != null) metrics.alloc();
        sort(array, buffer, 0, array.length, metrics);
    }


    private static <T extends Comparable<T>> void sort(T[] a, T[] buf, int lo, int hi, Metrics m) {
        if (m != null) m.enter();
        try {
            int n = hi - lo;
            if (n <= INSERTION_CUTOFF) {
                insertionSort(a, lo, hi, m);
                return;
            }
            int mid = lo + (n >> 1);
            sort(a, buf, lo, mid, m);
            sort(a, buf, mid, hi, m);
            merge(a, buf, lo, mid, hi, m);
        } finally {
            if (m != null) m.exit();
        }
    }


    private static <T extends Comparable<T>> void merge(T[] a, T[] buf, int lo, int mid, int hi, Metrics m) {
        int i = lo, j = mid, k = lo;
        while (i < mid || j < hi) {
            if (i < mid && (j >= hi || a[i].compareTo(a[j]) <= 0)) {
                if (m != null) m.comp();
                buf[k++] = a[i++];
            } else {
                if (m != null) m.comp();
                buf[k++] = a[j++];
            }
        }
        System.arraycopy(buf, lo, a, lo, hi - lo);
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