package pkg

/**
 * Illustration of in/out declaration/use site variance.
 */
object Generics {
    @JvmStatic
    fun main(args: Array<String>) {
        // NOTE: Array is invariant (unlike in Java)
        val arrInt = arrayOf(1, 2, 3, 4)
        val arrAny = Array<Any>(4) { Unit }

        // The 'out' projection indicates covariance for output methods.  Input methods cannot be used
        copy(arrInt, arrAny)

        arrAny.forEach { print("$it,") }
        println()

        val arrNum = Array<Number>(4) { 0L }
        // The 'in' projection indicates contravariance.  Input methods can be used.
        // NOTE: Passing Array<Number> in to Array<in Int> field.
        fill(arrNum, 99)
    }

    fun copy(src: Array<out Any>, dest: Array<Any>) {
        for (ix in src.indices) dest[ix] = src[ix]
        // not allowed do to 'out' projection
        // src[0] = 99
    }

    fun fill(dest: Array<in Int>, value: Int) {
        for (ix in dest.indices) dest[ix] = value
        // The type of x is Any?  i.e. Nothing can be assumed
        val x = dest[0]
    }
}