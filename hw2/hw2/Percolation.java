package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private boolean[][] sites;
    // represents whether site is open or not
    // true represents that site is open
    // false represents that site is not open
    private int numberOfOpenSites;

    private int N;

    private int virtualTop;
    private int virtualBottom;

    private int currentIndices(int row, int col) {
        return this.N * row + col;
    }

    //create N-by-N grid, with all sites initiall
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Wrong intput(N should > 0)");
        }
        this.N = N;
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(N * N + 2);
        // weightedQuickUnionUF does not contain N * N but contains N * N - 1
        this.virtualTop = this.N * this.N;
        this.virtualBottom = this.N * this.N + 1;
        for (int i = 0; i < this.N; i++) {
            this.weightedQuickUnionUF.union(currentIndices(0, i), this.virtualTop);
            this.weightedQuickUnionUF.union(currentIndices(this.N - 1, i), this.virtualBottom);
        }
        this.numberOfOpenSites = 0;
        this.sites = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.sites[i][j] = false;
            }
        }
    }

    //open the site (row, col) if it is not open
    public void open(int row, int col) {
        if (row < 0 || row > this.N - 1 || col < 0 || col > this.N - 1) {
            throw new IndexOutOfBoundsException("Wrong input" +
                    "(row and col should be integers between 0 and N - 1)");
        }
        if (!sites[row][col]) {
            this.sites[row][col] = true;
            this.numberOfOpenSites += 1;
            int currentIndices = currentIndices(row, col);
            int aboveIndices = currentIndices(row - 1, col);
            int belowIndices = currentIndices(row + 1, col);
            int leftIndices = currentIndices(row, col - 1);
            int rightIndices = currentIndices (row, col + 1);
            boolean above = true;
            boolean below = true;
            boolean left = true;
            boolean right = true;
            if (row == 0) {
                above = false;
            }
            if (row == this.N - 1) {
                below = false;
            }
            if (col == 0) {
                left = false;
            }
            if (col == this.N - 1) {
                right = false;
            }
            if (above && sites[row - 1][col]) {
                weightedQuickUnionUF.union(currentIndices, aboveIndices);
            }
            if (below && sites[row + 1][col]) {
                weightedQuickUnionUF.union(currentIndices, belowIndices);
            }
            if (left && sites[row][col - 1]) {
                weightedQuickUnionUF.union(currentIndices, leftIndices);
            }
            if (right && sites[row][col + 1]) {
                weightedQuickUnionUF.union(currentIndices, rightIndices);
            }
        }
    }

    //is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row > this.N - 1 || col < 0 || col > this.N - 1) {
            throw new IndexOutOfBoundsException("Wrong input" +
                    "(row and col should be integers between 0 and N - 1)");
        }
        return this.sites[row][col];
    }

    //is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row > this.N - 1 || col < 0 || col > this.N - 1) {
            throw new IndexOutOfBoundsException("Wrong input" +
                    "(row and col should be integers between 0 and N - 1)");
        }
        boolean res = false;
        int currentIndices = currentIndices(row, col);
        res = this.isOpen(row, col) &&
                this.weightedQuickUnionUF.connected(currentIndices, this.virtualTop);
        return res;
    }

    //number of open sites
    public int numberOfOpenSites() {
        return this.numberOfOpenSites;
    }

    //does the system percolate?
    public boolean percolates() {
        boolean res = false;
        if (this.N == 1) {
            res = this.isOpen(0, 0);
        } else {
            res = this.weightedQuickUnionUF.connected(this.virtualTop, this.virtualBottom);
        }
//        for (int top = 0; top < this.N; top++) {
//            int start = currentIndices(this.N - 1, 0);
//            int end = currentIndices(this.N - 1, this.N - 1);
//            for (int bottom = start; bottom <= end; bottom++) {
//                if (weightedQuickUnionUF.connected(top, bottom)) {
//                    res = true;
//                    break;
//                }
//            }
//        }
        return res;
    }

    //use for unit testing (not required)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
//        percolation.open(0, 0);
//        percolation.open(4, 0);
//        percolation.open(4, 4);
//        percolation.open(0, 4);
//        System.out.println(percolation.isOpen(0, 0));
//        System.out.println(percolation.isOpen(0, 4));
//        System.out.println(percolation.isOpen(4, 4));
//        System.out.println(percolation.isOpen(4, 0));
//        System.out.println(percolation.isFull(0, 0));
//        System.out.println(percolation.isFull(0, 4));
//        System.out.println(percolation.isFull(4, 4));
//        System.out.println(percolation.isFull(4, 0));
//        System.out.println(percolation.isFull(0, 1));
        System.out.println(percolation.percolates());
        System.out.println(percolation.numberOfOpenSites());
    }
}
