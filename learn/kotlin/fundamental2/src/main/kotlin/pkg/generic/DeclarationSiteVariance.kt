package pkg.generic

/**
 * Illustration of declaration-site variance.
 */
object DeclarationSiteVariance {
    @JvmStatic
    fun main(args: Array<String>) {
        val itStr: Iterator<String> = listOf("1", "2", "3").iterator()
        // Iterator has 'out' at declaration site => covariance
        // String is-a Any, therefore Iterator<String> is-a Iterator<Any>
        val itAny: Iterator<Any> = itStr

        while (itAny.hasNext()) print("${itAny.next()},")
        println()

        val compNumber = object: Comparable<Number> {
            override fun compareTo(n: Number) = 0
        }
        // Comparable hsa 'in' at declaration site => contravariance
        // Something that consumes Number can consume Double
        val compDouble: Comparable<Double> = compNumber
    }


}