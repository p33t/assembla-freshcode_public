package pkg


import java.io.File
import collection.immutable.List
import java.lang.String

object DesignChallenge {
  def main(args: Array[String]) {
    firstAttempt()
    secondAttempt()
    thirdAttempt()
  }

  def check(funct: (File, MyTable => Unit) => Stats) {
    val expected = Stats(3, 9, 8)
    val actual: Stats = funct(new File("."), {
      _: MyTable => () // do nothing
    })
    println("Should be " + expected + ": " + actual)
    println("expected " + (if (expected == actual) "" else "!") + "= actual")
  }

  /////////////////// Building Blocks ///////////////////
  def streamFiles(folder: File) = Stream("f1", "f2", "f3").map(new File(folder, _))

  def readParas(file: File) = {
    val secondFile = if (file.getName == "f2") "Bad" else "Good"
    List("Good", secondFile, "Good")
  }

  def dividePages(paras: List[String]) = paras.map(List(_))

  class MyTable

  def recogniseTable(paras: List[String]) = if (paras == List("Good")) Some(new MyTable) else None

  /////////////////// Third Attempt ////////////////////
  def parseDocs3(f: File, dest: MyTable => Unit): Stats = {
    def foldMyTable(count: Int, tab: MyTable): Int = {
      dest(tab)
      count + 1
    }

    def foldPage(pageTableCount: (Int, Int), page: List[String]): (Int, Int) = {
      val (oldPageCount, oldTableCount) = pageTableCount
      val tableOpt = recogniseTable(page)
      val tableCount = tableOpt.foldLeft(oldTableCount)(foldMyTable _)
      (oldPageCount + 1, tableCount)
    }

    def foldFile(filePageTableCount: (Int, Int, Int), file: File): (Int, Int, Int) = {
      val (oldFileCount, oldPageCount, oldTableCount) = filePageTableCount
      val paras: List[String] = readParas(file)
      val pages = dividePages(paras)
      val (pageCount, fileCount) = pages.foldLeft((oldPageCount, oldTableCount))(foldPage _)
      (oldFileCount + 1, pageCount, fileCount)
    }

    val fileStream = streamFiles(f)
    val (fileCount, pageCount, tableCount) = fileStream.foldLeft((0, 0, 0))(foldFile _)
    Stats(fileCount, pageCount, tableCount)
  }

  def thirdAttempt() {
    println("Third Attempt...")
    check(parseDocs3 _)
  }

  /////////////////// Second Attempt ////////////////////
  case class Stats(fileCount: Int, pageCount: Int, tableCount: Int)

  class CountingFilter[T] extends Function1[T, Boolean] {
    var count = 0

    def currentCount = count

    def apply(v1: T) = {
      count += 1
      true
    }
  }

  def parseDocs(f: File, dest: MyTable => Unit): Stats = {
    val fileCount = new CountingFilter[File]
    val files = streamFiles(f).filter(fileCount)
    val pageCount = new CountingFilter[List[String]]
    val pages = files.map(readParas _).flatMap(dividePages _).filter(pageCount)
    val tableCount = new CountingFilter[MyTable]
    val tables = pages.flatMap(recogniseTable(_)).filter(tableCount)
    tables.foreach(dest(_))
    Stats(fileCount.currentCount, pageCount.currentCount, tableCount.currentCount)
  }

  def secondAttempt() {
    println("\nSecond Attempt...")
    check(parseDocs _)
  }

  /////////////////// First Attempt ////////////////////
  val SourceFolder = new File(".")
  // pretend this is a global constant

  def pageStream = streamFiles(SourceFolder).map(readParas _).flatMap(dividePages _)

  def tableStream = pageStream.flatMap(recogniseTable(_))

  def firstAttempt() {
    println("\nFirst Attempt...\nShould be 8: " + tableStream.size)
  }

  ////////////////// Challenge I ////////////////////////
  // sourceFolder needs to be parameterised
  // avoid loading all files into memory at once
  // want stats on files, pages, models

  ////////////////// Challenge II //////////////////////
  // A nice clean way to test drive ?
  // Parallel processing ?
}