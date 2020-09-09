package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;


public class PercolationStats {
    private double[] results;
    // record the fraction of sites that are opened when the system percolates

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Wrong input(N and T should larger than zero)");
        }
        results = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation percolation = pf.make(N);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                // StdRandom.uniform(int a, int b) uniformly generate
                // integers between a(inclusive) and b(exclusive)
                percolation.open(row, col);
            }
            double fractionOfOpenToBlocked = (double) percolation.numberOfOpenSites() / (N * N);
            results[i] = fractionOfOpenToBlocked;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return this.mean() - 1.96 * this.stddev() / Math.pow(results.length, 0.5);
    }

    // high endpoin of 95% confidence interval
    public double confidenceHigh() {
        return this.mean() + 1.96 * this.stddev() / Math.pow(results.length, 0.5);
    }


    // for unit testing
//public static void main(String[] args) {
//    PercolationStats percolationStats = new PercolationStats(20, 10, new PercolationFactory());
//    System.out.println(percolationStats.mean());
//    System.out.println(percolationStats.stddev());
//}

}
