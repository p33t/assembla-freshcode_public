package pkg1

import java.io.{File, PrintWriter}

/**
 * Explore scala file reading (and Java file writing).  Maybe 'scala-io' is out of incubator.
 */
object FileIo {
  def main(args: Array[String]) {
    // NOTE: There is a nio.Files.createTempFile() too
    val f = File.createTempFile("FileIo_demo", ".txt")
    f.deleteOnExit()

    val writer = new PrintWriter(f, "utf-8")
    try {
      writer.println("first line")
      writer.println("second line")
    } finally {
      writer.close();
    }

    val someFileTxt = scala.io.Source.fromFile(f)
    for (line <- someFileTxt.getLines) {
      println(line)
    }
    someFileTxt.close()
  }
}