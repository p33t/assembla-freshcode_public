package pckg_2_10

import pckg_2_10.fixture.SomeClass

/**
 * Implicit access to the type args
 */
object ClassTagUsage {
  def main(args: Array[String]) {
    classOf[SomeClass]
  }

  def classOf[T](implicit tag: reflect.ClassTag[T]) {
    println("Class: " + tag.runtimeClass.getName)
  }
}