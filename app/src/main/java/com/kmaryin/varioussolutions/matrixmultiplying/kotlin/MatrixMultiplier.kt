package com.kmaryin.varioussolutions.matrixmultiplying.kotlin

/**
 * Created by konstantinmaryin on 2019-06-17.
 * All rights reserved
 */
class MatrixMultiplier(private val l: Int, private val m: Int, private val n: Int) {
    private val m1: Array<IntArray> = Array(l) { IntArray(m) }
    private val m2: Array<IntArray> = Array(m) { IntArray(n) }
    private val result: Array<IntArray> = Array(l) { IntArray(n) }
    private val asyncResult: Array<IntArray> = Array(l) { IntArray(n) }

    init {
        initMatrix(m1)
        initMatrix(m2)
    }

    private fun initMatrix(m: Array<IntArray>) {
        for (i in 0 until m.size) {
            for (j in 0 until m[i].size) {
                val nextRandom = Math.random()
                m[i][j] = (nextRandom * MaxNumber).toInt()
            }
        }
    }

    fun multiply() {
        for (i in 0 until l) {
            for (j in 0 until n) {
                var res = 0

                for (k in 0 until m) {
                    res += m1[i][k] * m2[k][j]
                }

                result[i][j] = res
            }
        }
    }

    fun multiplyAsync() {
        AsyncMultiplierExecutor(m1, m2, asyncResult, l, m, n).multiply(ThreadsCount)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        appendMatrixToStringBuilder(sb, m1)
        sb.append('\n').append('\n')

        appendMatrixToStringBuilder(sb, m2)
        sb.append('\n').append('\n')

        sb.append("\nSync:\n")
        appendMatrixToStringBuilder(sb, result)
        sb.append('\n').append('\n')

        sb.append("\nAsync:\n")
        appendMatrixToStringBuilder(sb, asyncResult)

        return sb.toString()
    }

    private fun appendMatrixToStringBuilder(sb: StringBuilder, m: Array<IntArray>) {
        for (i in 0 until m.size) {
            for (j in 0 until m[i].size) {
                sb.append(m[i][j])

                if (j < m[i].size - 1) {
                    sb.append(' ')
                }
            }

            if (i < m.size - 1) {
                sb.append('\n')
            }
        }
    }

    companion object {
        private const val MaxNumber = 10
        private const val ThreadsCount = 5
    }
}