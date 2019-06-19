package com.kmaryin.varioussolutions.matrixmultiplying.kotlin

class AsyncMultiplierExecutor(private val m1: Array<IntArray>, private val m2: Array<IntArray>,
                              private val result: Array<IntArray>, private val l: Int,
                              private val m: Int, private val n: Int) {

    @Volatile
    private var row: Int = 0
    @Volatile
    private var column: Int = 0

    private val locker: Any = Any()

    fun multiply(threadsCount: Int) {
        val threads = ArrayList<Thread>()
        for (i in 0 until threadsCount) {
            val t = Thread(MultiplyingTask())
            threads.add(t)
            t.start()
        }

        for (thread in threads) {
            thread.join()
        }
    }

    inner class MultiplyingTask : Runnable {
        override fun run() {
            while(true) {
                var currentRow: Int
                var currentColumn: Int

                synchronized(locker) {
                    if (column == n) {
                        currentRow = ++row
                        column = 0
                    } else {
                        currentRow = row
                    }

                    currentColumn = column++
                }

                if (currentRow >= l) break

                var res = 0
                for (i in 0 until m) {
                    res += m1[currentRow][i] * m2[i][currentColumn]
                }
                result[currentRow][currentColumn] = res
            }
        }
    }
}