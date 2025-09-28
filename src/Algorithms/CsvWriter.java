package Algorithms;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Simple CSV writer for logging experiment results.
 */
public class CsvWriter implements AutoCloseable {
    private final PrintWriter writer;

    public CsvWriter(String filename) throws IOException {
        this.writer = new PrintWriter(new FileWriter(filename, true)); // append mode
        // Если файл пустой — можно добавить заголовок
        if (filename.endsWith(".csv")) {
            writer.println("algorithm,n,size,time_ms,comparisons,allocations,depth");
            writer.flush();
        }
    }

    public void log(String algorithm, int n, long timeMs, Metrics m) {
        writer.printf("%s,%d,%d,%d,%d,%d%n",
                algorithm, n, timeMs, m.getComparisons(), m.getAllocations(), m.getMaxDepth());
        writer.flush();
    }

    @Override
    public void close() {
        writer.close();
    }
}

