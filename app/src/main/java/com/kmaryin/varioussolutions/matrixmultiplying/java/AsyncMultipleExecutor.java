package com.kmaryin.varioussolutions.matrixmultiplying.java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by konstantinmaryin on 2019-06-18.
 * All rights reserved
 */
public class AsyncMultipleExecutor {
    private final int[][] m1, m2, result;
    private final int l, m, n;

    private volatile int row;
    private volatile int column;

    private final Object locker = new Object();

    public AsyncMultipleExecutor(int[][] m1, int[][] m2, int[][] result, int l, int m, int n) {
        this.m1 = m1;
        this.m2 = m2;
        this.result = result;

        this.l = l;
        this.m = m;
        this.n = n;

        row = column = 0;
    }

    public void multiply(int threadsCount) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadsCount; ++i) {
            Thread t = new Thread(new MultiplyingTask());
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                continue;
            }
        }
    }

    private class MultiplyingTask implements Runnable {
        @Override
        public void run() {
            int currentRow;
            int currentColumn;

            synchronized (locker) {
                if (column == n - 1) {
                    currentRow = row++;
                    currentColumn = column = 0;
                } else {
                    currentRow = row;
                    currentColumn = column++;
                }

                if (currentRow == l - 1 && currentColumn == n) {
                    // Finish
                    return;
                }
            }

            int res = 0;
            for (int i = 0; i < m; ++i) {
                res += m1[currentRow][i] + m2[i][currentColumn];
            }

            result[currentRow][currentColumn] = res;
        }
    }
}
