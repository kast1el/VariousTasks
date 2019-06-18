package com.kmaryin.varioussolutions;

import com.kmaryin.varioussolutions.matrixmultiplying.java.MatrixMultiplier;

public class Runner {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        MatrixMultiplier multiplier = new MatrixMultiplier(2, 2, 2);
        multiplier.multiply();

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.print("Sync taken time: ");
        System.out.print(elapsedTime);
        System.out.println(" ms");

        startTime = System.currentTimeMillis();
        multiplier.multiplyAsync();
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.print("Async taken time: ");
        System.out.print(elapsedTime);
        System.out.println(" ms");

        System.out.println();
        System.out.println(multiplier.toString());
    }
}
