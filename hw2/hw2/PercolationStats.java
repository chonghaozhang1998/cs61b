package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;


public class PercolationStats {
    private double[] results; // record the fraction of sites that are opened when the system percolates

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
            double fractionOfOpenToBlocked = percolation.numberOfOpenSites() / (N * N);
            results[i] = fractionOfOpenToBlocked;
        }
    }

    //sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
//        double sum = 0;
//        for (double res : results) {
//            sum += res;
//        }
//        return sum / results.length;
    }

    //sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
//        if (results.length <= 1) {
//            throw new IllegalArgumentException("Times(T) should be larger than 1");
//        }
//        double variance = 0;
//        double mean = this.mean();
//        for (int i = 0; i < results.length; i++) {
//            variance += Math.pow (results[i] - mean, 2);
//        }
//        return Math.pow(variance / (results.length - 1), 0.5);
    }

    //low endpoint of 95% confidence interval
    public double confidenceLow() {
        return this.mean() - 1.96 * this.stddev() / Math.pow(results.length, 0.5);
    }

    //high endpoin of 95% confidence interval
    public double confidenceHigh() {
        return this.mean() + 1.96 * this.stddev() / Math.pow(results.length, 0.5);
    }

}
