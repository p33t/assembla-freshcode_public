package pkg.syntax

object Functions {
    @JvmStatic
    fun main(args: Array<String>) {
        simple(99)
        inferredReturnTypeExpression()
        noReturnType()
    }

//    This implies 'Unit' return type.
//    fun simple(arg0: Int) {
    fun simple(arg0: Int): Boolean {
        return arg0 > 0
    }

    fun inferredReturnTypeExpression() = "Hello"

    fun noReturnType() {
        println("Yay")
    }
}