@file:Suppress("unused")

package pkg.classes

object ClassDeclaration {
    class NoBraces

    class NoConstructorKeyWordIfSimpleHeader(str: String,
                                             /**
                                              * A property declared in constructor arg.
                                              */
                                             val int: Int) {
        // constructor arg direct use
        val strVal = str
        @Suppress("JoinDeclarationAndAssignment")
        val strLen: Int

        init {
            // initialization code goes here (IE: Primary Constructor)
            strLen = str.length
        }
    }

    class PrivateConstruct private constructor(val calculated: Int) {
        // calling the primary constructor from a secondary
        constructor (ip: String): this(ip.length) {
            println("Only after primary construction")
        }

        init {
            // this code will be called before body of secondary constructor
            println("Me first")
        }
    }

    class PrivateNoArgLikeJava private constructor() {
        // stuff
    }

    /**
     * Has a no-arg constructor added by JVM compiler (for Java interop)
     * because all constructor args have defaults.
     */
    class ImplicitNoArgConstructor(val ip: String = "")

    class NoPrimaryConstructor {
        val ipLen: Int
        constructor(ip: String) {
            ipLen = ip.length
        }

        constructor(explicitLen: Int) {
            ipLen = explicitLen
        }
    }

    /**
     * Classes are final unless 'open'.  So are methods.
     */
    open class BaseClass {
        val xLen: Int
        constructor(x: String) {
            xLen = x.length
        }

        open fun hello() = {
            "world"
        }
    }

    class SubClass : BaseClass("bruce")

    open class SubClass2 : BaseClass {
        // secondaries need to initialize parent if no primary constructor
        constructor(a0: String): super(a0)
        constructor(a1: Int): super(a1.toString())

        /**
         * Add 'final' to prevent further override (must be an open class).
         */
        final override fun hello() = {
            "bonjour"
        }
    }



}