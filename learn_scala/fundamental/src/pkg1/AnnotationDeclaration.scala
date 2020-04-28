package pkg1

import java.lang.annotation.{ElementType, Target}

import scala.annotation.StaticAnnotation

/**
 * Explore creation and usage of annotations.  Cannot extend Java annotations.
 */
object AnnotationDeclaration {
  class MyAnno(value: String) extends StaticAnnotation

  @MyAnno("some-val")
  val SomeVal = "some-val"

  class TheAnno extends MyAnno("the-anno")

  @TheAnno
  val AnotherVal = "another-val"

  class SomeClass {
// Not allowed.  See declaration below.
//    @MethodTarget
    def someMethod(arg: String) = {
      "Hello " + arg
    }
  }

  def main(args: Array[String]): Unit = {
    println(new SomeClass().someMethod("Yellow"))
  }
}

/*
Cannot extend Java annotations in Scala...
Error:(19, 6) class MethodTarget does not extend scala.annotation.Annotation
    @MethodTarget
*/
class MethodTarget extends Target {
  override def value(): Array[ElementType] = Array(ElementType.METHOD)

  override def annotationType() = classOf[MethodTarget]
}
