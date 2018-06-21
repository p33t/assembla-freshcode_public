package ch01

import io.reactivex.subjects.PublishSubject

object EvenVsOdd {
    @JvmStatic
    fun main(args: Array<String>) {
        val subject = PublishSubject.create<Int>()
        val altSubj = subject.map { isEven(it) }
        altSubj.subscribe { println("The number is ${if (it) "Even" else "Odd"}") }
        subject.onNext(4)
        subject.onNext(9)
    }

    private fun isEven(i: Int) = i % 2 == 0
}
