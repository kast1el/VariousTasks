package com.kmaryin.varioussolutions.matrixmultiplying.java;

/**
 * Created by konstantinmaryin on 2019-06-17.
 * All rights reserved
 */
public class MatrixMultiplier {
    private static final int MAX_NUMBER = 10;
    private static final int THREADS_COUNT = 5;

    private final int[][] m1;
    private final int[][] m2;
    private final int[][] result;
    private final int[][] asyncResult;

    private final int l;
    private final int m;
    private final int n;

    public MatrixMultiplier(final int l, final int m, final int n) {
        this.l = l;
        this.m = m;
        this.n = n;

        m1 = new int[l][m];
        m2 = new int[m][n];
        result = new int[l][n];
        asyncResult = new int[l][n];

        initMatrix(m1);
        initMatrix(m2);
    }

    private void initMatrix(int[][] m) {
        int rows = m.length;
        for (int i = 0; i < rows; ++i) {
            int cols = m[i].length;
            for (int j = 0; j < cols; ++j) {
                double nextRandom = Math.random();
                m[i][j] = (int) (nextRandom * MAX_NUMBER);
            }
        }
    }

    public void multiply() {
        for (int i = 0; i < l; ++i) {
            for (int j = 0; j < n; ++j) {
                int res = 0;

                for (int k = 0; k < m; ++k) {
                    res += m1[i][k] * m2[k][j];
                }

                result[i][j] = res;
            }
        }
    }

    public void multiplyAsync() {
        new AsyncMultiplyingExecutor(m1, m2, asyncResult, l, m, n).multiply(THREADS_COUNT);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        appendMatrixToStringBuilder(sb, m1);
        sb.append('\n').append('\n');

        appendMatrixToStringBuilder(sb, m2);
        sb.append('\n').append('\n');

        sb.append("\nSync:\n");
        appendMatrixToStringBuilder(sb, result);
        sb.append('\n').append('\n');

        sb.append("\nAsync:\n");
        appendMatrixToStringBuilder(sb, asyncResult);

        return sb.toString();
    }

    private void appendMatrixToStringBuilder(StringBuilder sb, int[][] m) {
        int rows = m.length;
        for (int i = 0; i < rows; ++i) {
            int cols = m[i].length;
            for (int j = 0; j < cols; ++j) {
                sb.append(m[i][j]);
                if (j < cols - 1) {
                    sb.append(' ');
                }
            }
            if (i < rows - 1) {
                sb.append('\n');
            }
        }
    }
}
