package pkg

import java.util.*

object PropertyAndField {
    @JvmStatic
    fun main(args: Array<String>) {
        val host = Host()
        println(host)
        host.name = DEFAULT_NAME
        assert(!host.hasName)
        host.id = 99
        println(host)
        host.name = "bruce"
        assert(host.hasName)
        println(host)
        host.name = null
        assert(!host.hasName)
        println(host)

        host.init()
        println(host)
        try {
            host.init()
        }
        catch (e: RuntimeException) {
            // expected
        }
    }

    const val DEFAULT_NAME = "(no name)"

    /**
     * My properties look like class fields but they appear as getter/setter in Java.
     */
    class Host {
        var id: Int = 0

        /**
         * Custom get and set intercept
         */
        var name: String? = null
        get () = if (field == null) DEFAULT_NAME else field
        set(value) {
            if (value == null || value == DEFAULT_NAME) field = null
            field = value
        }

        /**
         * Type inferred and only readable.
         */
        val hasName get() = name != null

        override fun toString(): String {
            return "name: $name, id: $id, ${if (this::couchPotato.isInitialized) couchPotato else ""}"
            // causes exception , $couchPotato"
        }

        lateinit var couchPotato: String

        fun init() {
            if (this::couchPotato.isInitialized) throw RuntimeException("Already initialized")
            couchPotato = Date().toString()
        }
    }
}