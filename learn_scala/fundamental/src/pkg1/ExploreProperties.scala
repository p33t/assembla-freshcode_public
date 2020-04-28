package pkg1

import java.io.StringReader
import java.util.Properties

/**
 * Explore Java Properties manipulation.
 */
object ExploreProperties {
  def main(args: Array[String]) {
    confirmOverwrites()
  }

  private def confirmOverwrites() {
    val ps = new Properties()
    ps.setProperty("a.prop", "a value")
    ps.load(new StringReader("a.prop=another value"))
    require("another value" == ps.getProperty("a.prop"))
  }
}