package Algorithms;


import java.util.Arrays;


public class ClosestPair {
    public static class Point implements Comparable<Point> {
        public final double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
        public int compareTo(Point o) { return Double.compare(x, o.x); }
    }


    public static double closest(Point[] pts, Metrics m) {
        Point[] byX = pts.clone();
        Arrays.sort(byX);
        Point[] byY = pts.clone();
        Arrays.sort(byY, (a, b) -> Double.compare(a.y, b.y));
        return rec(byX, byY, 0, pts.length, m);
    }


    private static double rec(Point[] byX, Point[] byY, int lo, int hi, Metrics m) {
        int n = hi - lo;
        if (n <= 3) return bruteForce(byX, lo, hi);
        int mid = lo + n/2;
        double midx = byX[mid].x;


        Point[] leftY = new Point[mid - lo];
        Point[] rightY = new Point[hi - mid];
        int li = 0, ri = 0;
        for (Point p : byY) {
            if (p.x <= midx && li < leftY.length) leftY[li++] = p;
            else if (ri < rightY.length) rightY[ri++] = p;
        }


        double dl = rec(byX, leftY, lo, mid, m);
        double dr = rec(byX, rightY, mid, hi, m);
        double d = Math.min(dl, dr);


        Point[] strip = Arrays.stream(byY).filter(p -> Math.abs(p.x - midx) < d).toArray(Point[]::new);
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && j <= i + 7; j++) {
                d = Math.min(d, dist(strip[i], strip[j]));
            }
        }
        return d;
    }

    private static double bruteForce(Point[] pts, int lo, int hi) {
        double best = Double.POSITIVE_INFINITY;
        for (int i = lo; i < hi; i++) {
            for (int j = i + 1; j < hi; j++) {
                best = Math.min(best, dist(pts[i], pts[j]));
            }
        }
        return best;
    }


    private static double dist(Point a, Point b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return Math.hypot(dx, dy);
    }
}