package pkg


object FileIo {
  def main(args: Array[String]) {
    for (line <- scala.io.Source.fromFile("src/some-file.txt").getLines) {
      println(line)
    }
  }
}