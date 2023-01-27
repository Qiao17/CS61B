package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

public class Percolation {
    public WeightedQuickUnionUF site;
    public int N;
    public int[] openSta;
    /** create N-by-N grid, with all sites initially blocked
     * */
    public Percolation(int Num) {
        int sum = Num * Num;
        site = new WeightedQuickUnionUF(sum + 2);
        N = Num;
        openSta = new int[sum];
    }

    public int xyTo1D(int r, int c) {
        return r * N + c;
    }

    /** open the site (row, col) if it is not open already
     */
    public void open(int row, int col) {
        int curr = xyTo1D(row, col);
        openSta[curr] = 1;

        // virtual top site
        if (curr < N) {
            site.union(curr, N * N);
        }

        // virtual down site
        if (curr >= N * (N - 1) && isFull(row, col)) {
            site.union(curr, N * N + 1);
        }

        // up
        if (row > 0) {
            int up = xyTo1D(row - 1, col);
            if (isOpen(row - 1, col)) {
                site.union(curr, up);
            }
        }

        // down
        if (row < N - 1) {
            int down = xyTo1D(row + 1, col);
            if (isOpen(row + 1, col)) {
                site.union(curr, down);
            }
        }

        // left
        if (col > 0) {
            int left = xyTo1D(row, col - 1);
            if (isOpen(row, col - 1)) {
                site.union(curr, left);
            }
        }

        // right
        if (col < N - 1) {
            int right = xyTo1D(row, col + 1);
            if (isOpen(row, col + 1)) {
                site.union(curr, right);
            }
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return openSta[xyTo1D(row, col)] == 1;
    }

    /* is the site (row, col) full?
    A full site is an open site that can be connected to an open site in the top row via a chain of
    neighboring (left, right, up, down) open sites.
     */
    public boolean isFull(int row, int col) {
        if (!isOpen(row, col)) {
            return false;
        }
        int curr = xyTo1D(row, col);
        return site.connected(curr, N * N);
    }

    // number of open sites
    public int numberOfOpenSites() {
        int sum = 0;
        for (int x: openSta) {
            if (x == 1) {
                sum += 1;
            }
        }
        return sum;
    }

    // does the system percolate?
    public boolean percolates() {
        return site.connected(N * N, N * N + 1);
    }
}
