package hw2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    public double[] threshold;
    int T;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        threshold = new double[T];
        this.T = T;
        int count;
        int row;
        int col;
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            count = 0;
            while (!p.percolates()) {
                row = StdRandom.uniform(N);
                col = StdRandom.uniform(N);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    count += 1;
                }
            }
            threshold[i] = (double)count / (N * N);
        }


    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(threshold);
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(threshold);
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

}
