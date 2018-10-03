package pkg

import scala.annotation.StaticAnnotation

object AnnotationDeclaration {
  class MyAnno(value: String) extends StaticAnnotation

  @MyAnno("some-val")
  val SomeVal = "some-val"

  class TheAnno extends MyAnno("the-anno")

}

/*
No can do...
Warning:(17, 7) Implementation restriction: subclassing Classfile does not
make your annotation visible at runtime.  If that is what
you want, you must write the annotation class in Java.
class MethodTarget extends Target {

class MethodTarget extends Target {
  override def value(): Array[ElementType] = Array(ElementType.METHOD)

  override def annotationType() = classOf[MethodTarget]
}
*/
