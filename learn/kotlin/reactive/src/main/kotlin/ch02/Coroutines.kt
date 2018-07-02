package ch02

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

object Coroutines {
    @JvmStatic
    fun main(args: Array<String>) {
        // blocking the main thread
        runBlocking {
            //(4)
            val exeTime = longRunningTsk()//(5)
            println("Execution Time is $exeTime")
        }

        // using coroutine
        val time = async(CommonPool) { longRunningTsk() }//(1)
        println("Print after async ")
        runBlocking { println("printing time ${time.await()}") }//(2)
    }

    private suspend fun longRunningTsk(): Long {//(1)
        return measureTimeMillis {
            //(2)
            println("Please wait")
            delay(2, TimeUnit.SECONDS)//(3)
            println("Delay Over")
        }
    }
}