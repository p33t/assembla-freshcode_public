package pkg


object Extractor {
  def main(args: Array[String]) {
    val sp = "bruce-lee"
    sp match {
      case StringPair(Array("bruce", "willis")) => println("weird")
      case StringPair(Array("bruce", "lee")) => println("normal")
    }
  }


  object StringPair {
    def unapply(sp: String): Option[Array[String]] = {
      if (sp.contains("-")) Some(sp.split("-"))
      else None
    }
  }

}