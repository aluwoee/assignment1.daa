package Algorithms;


public class DeterministicSelect {
    public static <T extends Comparable<T>> T select(T[] a, int k, Metrics m) {
        return select(a, 0, a.length - 1, k, m);
    }


    private static <T extends Comparable<T>> T select(T[] a, int lo, int hi, int k, Metrics m) {
        if (lo == hi) return a[lo];
        if (m != null) m.enter();
        try {
            int n = hi - lo + 1;
            if (n <= 10) {
                insertionSort(a, lo, hi + 1, m);
                return a[lo + k];
            }
            int numMedians = partitionIntoMedians(a, lo, hi, m);
            T pivot = select(a, lo, lo + numMedians - 1, numMedians / 2, m);
            int pivotIndex = partitionAroundPivot(a, lo, hi, pivot, m);
            int rank = pivotIndex - lo;
            if (k == rank) return a[pivotIndex];
            else if (k < rank) return select(a, lo, pivotIndex - 1, k, m);
            else return select(a, pivotIndex + 1, hi, k - rank - 1, m);
        } finally {
            if (m != null) m.exit();
        }
    }


    private static <T extends Comparable<T>> int partitionIntoMedians(T[] a, int lo, int hi, Metrics m) {
        int numMedians = 0;
        for (int i = lo; i <= hi; i += 5) {
            int r = Math.min(4, hi - i);
            insertionSort(a, i, i + r + 1, m);
            int medianIndex = i + r / 2;
            swap(a, lo + numMedians, medianIndex);
            numMedians++;
        }
        return numMedians;
    }

    private static <T extends Comparable<T>> int partitionAroundPivot(T[] a, int lo, int hi, T pivot, Metrics m) {
        int i = lo, j = hi;
        while (i <= j) {
            while (i <= hi && a[i].compareTo(pivot) < 0) { if (m != null) m.comp(); i++; }
            while (j >= lo && a[j].compareTo(pivot) > 0) { if (m != null) m.comp(); j--; }
            if (i <= j) swap(a, i++, j--);
        }
        return j + 1;
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



