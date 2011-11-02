package pkg

import reflect.ClassManifest


object Manifests {
  def main(args: Array[String]) {
    classOp[Bruce]

    val cls = classOf[Bruce]
    classOp(ClassManifest.fromClass(cls))
  }

  def classOp[T](implicit mf: ClassManifest[T]) {
    println("You specified a " + mf.erasure.getSimpleName)
  }

  class Bruce

}