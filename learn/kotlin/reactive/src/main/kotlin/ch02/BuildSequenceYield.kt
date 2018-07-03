package ch02

import kotlin.coroutines.experimental.buildSequence

// This requires coroutines feature
object BuildSequenceYield {
    @JvmStatic
    fun main(args: Array<String>) {
        val fibonacciSeries = buildSequence {
            //(1)
            var a = 0
            var b = 1
            yield(a)//(2)
            yield(b)
            while (true) {
                val c = a + b
                yield(c)//(3)
                a = b
                b = c
            }
        }
        println(fibonacciSeries.take(10).joinToString(","))//(4)
    }
}