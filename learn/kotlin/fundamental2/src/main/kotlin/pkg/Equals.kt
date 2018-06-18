package pkg

object Equals {
    @JvmStatic
    fun main(args: Array<String>) {
        val hello = "Hello"
        val h2 = hello
        assert(hello === h2)
        val h3 = String(hello.toCharArray())
        assert(hello !== h3)
        assert(hello == h3)
    }
}