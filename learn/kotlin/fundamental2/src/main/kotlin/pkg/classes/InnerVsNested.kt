package pkg.classes

object InnerVsNested {

    class Outer {
        val myField = 99
        // this is actually 'static'
        class Nested {
            init {
                // won't compile
//                println(myField)
            }
        }

        // this can access outer elements
        inner class Inner {
            init {
                println(myField)
                val theOuter = this@Outer
                println(theOuter)
            }
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val outer = Outer()
        val nested = Outer.Nested() // does not require instance
        val inner = outer.Inner()
        println("$outer $nested $inner")
    }
}