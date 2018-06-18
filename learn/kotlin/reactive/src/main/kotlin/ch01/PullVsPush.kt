package ch01

import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable

object PullVsPush {

    @JvmStatic
    fun main(args: Array<String>) {
        println("Pull...")
        pull()
        println("Push...")
        push()
    }

    private fun pull() {
        val list: List<Any> = listOf("One", 2, "Three", "Four", 4.5, "Five", 6.0f) // 1
        val iterator = list.iterator() // 2
        while (iterator.hasNext()) { // 3
            println(iterator.next()) // Prints each element 4
        }
    }

    private fun push() {
        val list: List<Any> = listOf("One", 2, "Three", "Four", 4.5, "Five", 6.0f) // 1
        val observable: Observable<Any> = list.toObservable()
        observable.subscribeBy( // named arguments for lambda Subscribers
                onNext = { println(it) },
                onError = { it.printStackTrace() },
                onComplete = { println("Done!") }
        )
    }
}