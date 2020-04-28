package pkg1

/**
 * Automatic use of 'unapply' method to interpret/deconstruct a value (E.g. stringified terms) during pattern match.
 */
object Extractor {
  def main(args: Array[String]) {
    def matchThis(s: String) {
      s match {
        case StringPair("bruce", "willis") => println("weird")
        case StringPair("bruce", "lee") => println("normal")
          // Apparently this is called 'infix notation'.
          // See https://github.com/dpp/simply_lift/blob/master/samples/http_rest/src/main/scala/code/lib/BasicWithHelper.scala#L46
          // And http://stackoverflow.com/questions/4008271/explain-this-pattern-matching-code/4008797#4008797
        case "bruce" StringPair "springsteen" => println("strange syntax")
      }
    }


    matchThis("bruce-lee")
    matchThis("bruce-springsteen")
  }


  object StringPair {
    def unapply(sp: String): Option[(String,String)] = {
      val ix = sp.indexOf('-')
      if (ix >= 0) Some((sp.substring(0, ix), sp.substring(ix + 1)))
      else None
    }
  }

}