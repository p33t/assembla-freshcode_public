package pckg_2_10

import scala.collection.JavaConverters
import JavaConverters._

object JavaConvertersDemo {
  def main(args: Array[String]) {
    listOp()
    iterOp()
  }

  def iterOp() {
    val it: java.lang.Iterable[String] = freshList()
    it.asScala.foreach(println)
  }

  def listOp() {
    val l: java.util.List[String] = freshList()
    l.asScala.foreach(println)
  }

  def freshList() = {
    val l = new java.util.ArrayList[String]()
        l.add("one")
        l.add("two")
        l.add("three")
    l
  }
}
