package Algorithms;


import java.util.concurrent.atomic.AtomicLong;


/**
 * Tracks performance metrics: comparisons, allocations, recursion depth.
 */
public class Metrics {
    private final AtomicLong comparisons = new AtomicLong();
    private final AtomicLong allocations = new AtomicLong();
    private final ThreadLocal<Integer> depth = ThreadLocal.withInitial(() -> 0);
    private final AtomicLong maxDepth = new AtomicLong();


    public void enter() {
        int d = depth.get() + 1;
        depth.set(d);
        maxDepth.updateAndGet(x -> Math.max(x, d));
    }


    public void exit() {
        depth.set(Math.max(0, depth.get() - 1));
    }


    public void comp() { comparisons.incrementAndGet(); }
    public void alloc() { allocations.incrementAndGet(); }


    public long getComparisons() { return comparisons.get(); }
    public long getAllocations() { return allocations.get(); }
    public long getMaxDepth() { return maxDepth.get(); }


    public void reset() {
        comparisons.set(0);
        allocations.set(0);
        depth.set(0);
        maxDepth.set(0);
    }
}