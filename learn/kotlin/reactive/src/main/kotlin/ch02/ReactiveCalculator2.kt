package ch02

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.regex.Matcher
import java.util.regex.Pattern

class ReactiveCalculator2(a: Int, b: Int) {
    internal val subjectCalc: Subject<Pair<Int, Int>> = PublishSubject.create()
    internal var nums: Pair<Int, Int> = Pair(0, 0)

    init {
        subjectCalc.subscribe {
            // NOTE: My personal attempt to refactor (not from book)
            calc(it, {it.first + it.second}, "Add")
            calc(it, {it.first * it.second}, "Multiply")
        }
        nums = Pair(a, b)
        subjectCalc.onNext(nums)
    }

    private fun calc(pair: Pair<Int, Int>, op: (Pair<Int, Int>) -> Int, name: String): Int {
        val result = op(pair)
        println("$name = $result")
        return result
    }

    fun modifyNumbers(a: Int = nums.first, b: Int = nums.second) {
        nums = Pair(a, b)
        subjectCalc.onNext(nums)
    }

    fun handleInput(inputLine: String?) {
        if (!inputLine.equals("exit")) {
            val pattern: Pattern = Pattern.compile("([a|b])(?:\\s)?=(?:\\s)?(\\d*)")
            var a: Int? = null
            var b: Int? = null
            val matcher: Matcher = pattern.matcher(inputLine)
            if (matcher.matches() && matcher.group(1) != null
                    && matcher.group(2) != null) {
                if (matcher.group(1).toLowerCase().equals("a")) {
                    a = matcher.group(2).toInt()
                } else if (matcher.group(1).toLowerCase().equals("b")) {
                    b = matcher.group(2).toInt()
                }
            }
            when {
                a != null && b != null -> modifyNumbers(a, b)
                a != null -> modifyNumbers(a = a)
                b != null -> modifyNumbers(b = b)
                else -> println("Invalid Input")
            }
        }
    }
}