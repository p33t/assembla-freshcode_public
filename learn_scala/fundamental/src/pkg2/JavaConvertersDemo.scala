package pkg2

import fixture.AnIterable

import scala.jdk.CollectionConverters._

/**
 * [[scala.collection.JavaConverters]] are successors to [[scala.concurrent.JavaConversions]].  The later uses implicits.
 * [[scala.jdk.CollectionConverters]] are successors to [[scala.collection.JavaConverters]].
 */
object JavaConvertersDemo {
  def main(args: Array[String]) {
    listOp()
    iterOp()
  }

  def iterOp() {
    val it: java.lang.Iterable[String] = freshList()
    it.asScala.foreach(println)

    new AnIterable().asScala.foreach(println)
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
