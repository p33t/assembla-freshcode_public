package pckg_2_10

import pckg_2_10.fixture.SomeClass

// import scala.reflect.runtime.universe._

/**
 * Implicit access to the type args
 */
object TypeArgDemo {
  def main(args: Array[String]) {
    classOp1[SomeClass]()
    classOp2[SomeClass]()
    classOp3[SomeClass]()
    classOp4[BruceLee]()
  }

  def classOp1[T]()(implicit manifest: Manifest[T]) {
    println("Class: " + manifest.runtimeClass.getName)
  }

  def classOp2[T <: Any : Manifest]() {
    println("Class: " + manifest[T].runtimeClass.getName)
  }

  def classOp3[T]()(implicit tag: reflect.ClassTag[T]) {
    println("Class: " + tag.runtimeClass.getName)
  }

  // Looks like scala 2.11 does bounds now.
  def classOp4[T <: Bruce : Manifest]() {
    println("Class: "  + manifest[T].runtimeClass.getName)
  }

  //  def classOp4[T : TypeTag]() {
  //    println("Class: " + typeTag[T].runtimeClass.getName)
  //  }

  class Bruce

  class BruceLee extends Bruce
}