package pkg
/** Trying to figure out Xlint scalac args.  See 'scalac -Xlint:help' */
object UnusedParamSpike {
    def main(args: Array[String]) {
      println("Hi from Unused Param Spike")
      println("Want x: " + unusedParams(99, "x"))
    }

    private def unusedParams(a: Int, b: String) = b
}

