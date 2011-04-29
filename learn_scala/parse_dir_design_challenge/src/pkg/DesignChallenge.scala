package pkg


import java.io.File

object DesignChallenge {
  /////////////////// Building Blocks ///////////////////
  def streamFiles(folder: File) = Stream("f1", "f2", "f3").map(new File(folder, _))

  def readParas(file: File) = {
    val secondFile = if (file.getName == "f2") "Bad" else "Good"
    List("Good", secondFile, "Good")
  }

  def dividePages(paras: List[String]) = paras.map(List(_))

  class MyTable

  def recogniseTable(paras: List[String]) = if (paras == List("Good")) Some(new MyTable) else None

  /////////////////// First Attempt ////////////////////
  val SourceFolder = new File(".") // pretend this is a global constant

  def pageStream = streamFiles(SourceFolder).map(readParas _).flatMap(dividePages _)

  def tableStream = pageStream.flatMap(recogniseTable(_))

  def firstAttempt() {
    println("Should be 8: " + tableStream.size)
  }

  def main(args: Array[String]) {
    firstAttempt()
  }

  ////////////////// Challenge I ////////////////////////
  // sourceFolder needs to be parameterised
  // avoid loading all files into memory at once
  // want stats on files, pages, models

  ////////////////// Challenge II //////////////////////
  // A nice clean way to test drive ?
  // Parallel processing ?
}