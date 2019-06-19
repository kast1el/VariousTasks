package com.kmaryin.varioussolutions;

import com.kmaryin.varioussolutions.matrixmultiplying.java.MatrixMultiplier;

public class Runner {

    public static void main(String[] args) {
        //new Runner().multiplyMatrices_Java();
        new Runner().multiplyMatrices_Kotlin();
    }

    private void multiplyMatrices_Java() {
        long startTime = System.currentTimeMillis();

        MatrixMultiplier multiplier = new MatrixMultiplier(5, 5, 5);
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

    private void multiplyMatrices_Kotlin() {
        long startTime = System.currentTimeMillis();

        com.kmaryin.varioussolutions.matrixmultiplying.kotlin.MatrixMultiplier multiplier =
                new com.kmaryin.varioussolutions.matrixmultiplying.kotlin.MatrixMultiplier(2, 2, 2);
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
