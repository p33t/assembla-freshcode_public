package ch02

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async

object ReactiveCalculator2Cli {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Initial Out put with a = 15, b = 10")
        val calculator = ReactiveCalculator2(15, 10)
        println("Enter a = <number> or b = <number> in separate lines\nexit to exit the program")
        var line = readLine()
        while (line != null && !line.toLowerCase().contains("exit")) {
            @Suppress("DeferredResultUnused")
            async {
                calculator.handleInput(line)
            }
            line = readLine()
        }
    }
}