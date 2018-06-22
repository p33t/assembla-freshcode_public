package pkg.syntax

import java.util.*

object Syntax {
    @JvmStatic
    fun main(args: Array<String>) {
        val rand = Random()

        // String Templates
        val x = "ex"
        println("$x-factor length ${x.length}")

        //if expression
        val evenOrOdd = if (rand.nextBoolean()) "Even" else "Odd"
        println("Number is $evenOrOdd")

        // nullable
        val maybe = if (rand.nextBoolean()) 99 else null
        val msg = if (maybe == null) "no value" else "value + 1: " + (maybe + 1)
        println(msg)

        // is
        val remark = if (maybe is Int) "An int" else "something else"
        println("maybe is $remark")

        // loops and lists
        val elems = listOf("one", "two", "three")
        for (elem in elems.reversed()) {
            for (ix in elems.indices) {
                if (elems[ix] == elem) println("found $elem at $ix")
            }
        }

        // when
        val obj: Any = if (rand.nextBoolean()) rand.nextDouble() else rand.nextInt()
        val comment = when (obj) {
            1 -> "Holy cow"
            is Int -> "Meh"
            is Boolean -> "WTF"
            is Double -> "Finally"
            else -> "You're kidding right?"
        }
        println("$obj was $comment")

        // ranges
        val myRange = 0..5
        val outcome = if (rand.nextInt(10) in myRange) "Score" else "Miss"
        println("Outcome: $outcome")

        for (i in myRange step 2) println("step $i")

        // collections
        val nums = listOf(3, 1, 4, 1, 5, 9)
        val foundNum = when {
            rand.nextInt(10) in nums -> "Hallelujah"
            else -> "Bzzzt"
        }
        println(foundNum)
        nums.filter { it % 2 == 1 }
                .sortedBy { 10 - it }
                .map { it * 3 }
                .forEach { println(it) }
    }
}