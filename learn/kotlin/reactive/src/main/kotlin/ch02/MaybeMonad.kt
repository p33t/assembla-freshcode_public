package ch02

import io.reactivex.Maybe
import io.reactivex.rxkotlin.subscribeBy

object MaybeMonad {
    @JvmStatic
    fun main(args: Array<String>) {
        val maybeValue: Maybe<Int> = Maybe.just(14)//1
        maybeValue.subscribeBy(//2
                onComplete = handleComplete,
                onError = handleError,
                onSuccess = handleSuccess
        )
        val maybeEmpty: Maybe<Int> = Maybe.empty()//3
        maybeEmpty.subscribeBy(
                onComplete = handleComplete,
                onError = handleError,
                onSuccess = handleSuccess
        )
    }

    private val handleError = { t: Throwable -> println("Error $t") }
    private val handleComplete = { println("Completed Empty") }
    private val handleSuccess = { v: Any -> println("Completed with value $v") }
}