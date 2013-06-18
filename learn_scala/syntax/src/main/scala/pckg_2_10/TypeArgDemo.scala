package pckg_2_10

import pckg_2_10.fixture.SomeClass

/**
 * Implicit access to the type args
 */
object TypeArgDemo {
  def main(args: Array[String]) {
    classOp1[SomeClass]()
    classOp2[SomeClass]()
  }

  def classOp1[T]()(implicit tag: reflect.ClassTag[T]) {
    println("Class: " + tag.runtimeClass.getName)
  }

  def classOp2[T <: Any : Manifest]() {
    println("Class: " + manifest[T].runtimeClass.getName)
  }
}