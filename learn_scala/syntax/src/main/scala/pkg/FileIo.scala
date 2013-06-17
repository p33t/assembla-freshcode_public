package pkg

object FileIo {
  def main(args: Array[String]) {
    for (line <- scala.io.Source.fromFile("src/some-file.txt").getLines) {
      println(line)
    }

    // no file writing yet... maybe Scala 2.9 will have it.  Otherwise get scala-incubator/scala-io...
    // https://github.com/scala-incubator/scala-io
  }
}