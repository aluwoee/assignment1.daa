# Algorithms Assignment 1

## Learning Goals
- Implement classic **divide-and-conquer algorithms** with safe recursion patterns.  
- Analyze running-time recurrences using **Master Theorem** and **Akra–Bazzi**.  
- Collect metrics (time, recursion depth, comparisons/allocations) and validate results experimentally.  
- Communicate findings via report and Git history.  

---

## Implemented Algorithms
- **MergeSort** (with buffer reuse and cutoff to insertion sort).  
- **QuickSort** (randomized pivot, smaller-first recursion, bounded stack depth).  
- **Deterministic Select** (Median-of-Medians, O(n) selection).  
- **Closest Pair of Points** (2D plane, O(n log n) divide-and-conquer).  

---

## Architecture Notes
- All algorithms share a **`Metrics` utility** to track:
  - comparisons, allocations, recursion depth.  
- `CsvWriter` logs results to `results.csv` for plotting.  
- `Main.java` executes experiments with randomized data.  
- Recursion depth is **bounded**:
  - QuickSort: always recurses into the smaller side, ensuring O(log n) depth.  
  - MergeSort: depth limited to O(log n).  
  - Select: recurses only on the relevant side.  
  - Closest Pair: classic O(log n) recursion.  

---

## Recurrence Analysis

### MergeSort

T(n) = 2T(n/2) + Θ(n)
Master Theorem Case 2 → **Θ(n log n)**.  

### QuickSort
Recurrence (expected with random pivot):  

T(n) = T(k) + T(n-k-1) + Θ(n)
Average-case depth = O(log n).  
Expected complexity → **Θ(n log n)**.  

### Deterministic Select (Median-of-Medians)
Recurrence:  

T(n) = T(n/5) + T(7n/10) + Θ(n)
By Akra–Bazzi, solves to **Θ(n)**.  

### Closest Pair of Points
Recurrence:  
T(n) = 2T(n/2) + Θ(n)
Master Theorem Case 2 → **Θ(n log n)**.  

---

## Experimental Setup
- Machine: Intel i5 / 16 GB RAM / Java 17.  
- Random input arrays generated for each run.  
- Metrics collected:
  - running time (ms),  
  - comparisons,  
  - allocations,  
  - recursion depth.  

---

## Example Results (`results.csv`)


---

## Plots
- **Time vs n**: QuickSort slightly faster than MergeSort due to better cache locality.  
- **Depth vs n**: MergeSort grows ~log n; QuickSort bounded by ~2 log₂ n.  
- **Select**: linear growth; slope higher than QuickSelect but asymptotics confirmed.  
- **Closest Pair**: matches O(n log n), brute-force validation consistent for small n.  

---

## Discussion
- **Theory vs practice**: experimental results align with expected asymptotics.  
- **Constant factors**:
  - QuickSort wins in practice (fewer allocations, cache-friendly).  
  - MergeSort requires extra buffer but stable.  
  - Deterministic Select is slower but robust (guaranteed O(n)).  
  - Closest Pair is practical and scales well.  
- Small `n`: insertion sort cutoff improves performance significantly.  

---

## Git Workflow
- **main** — stable releases (`v0.1`, `v1.0`).  
- **feature branches**:
  - `feature/mergesort`  
  - `feature/quicksort`  
  - `feature/select`  
  - `feature/closest`  
  - `feature/metrics`  

Commit storyline:
- `init`: maven, junit5, ci, readme  
- `feat(metrics)`: add counters, depth tracker, CSV writer  
- `feat(mergesort)`: implement mergesort with cutoff + tests  
- `feat(quicksort)`: randomized pivot + smaller-first recursion + tests  
- `feat(select)`: deterministic select (MoM5) + tests  
- `feat(closest)`: divide-and-conquer closest pair + tests  
- `feat(cli)`: run algos, emit CSV  
- `bench(jmh)`: benchmark select vs sort  
- `docs(report)`: add recurrences + plots  
- `fix`: handle duplicates, tiny arrays  
- `release`: v1.0  

---

## Summary
- MergeSort and QuickSort confirmed **Θ(n log n)**.  
- Deterministic Select confirmed **Θ(n)**.  
- Closest Pair confirmed **Θ(n log n)**.  
- Experiments match theory with minor constant-factor differences.  
- Git workflow demonstrates modular development with clean commits.  

Recurrence:  

