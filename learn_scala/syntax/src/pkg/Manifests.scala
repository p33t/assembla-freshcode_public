package pkg

import reflect.ClassManifest


object Manifests {
  def main(args: Array[String]) {
    classOp[Bruce]

    val cls = classOf[Bruce]
    classOp(ClassManifest.fromClass(cls))

    val l = List[Class[_]](cls)

    l match {
      case typelessClass :: _ =>
        // Not really sure what is going on here.
        // Thought I'd need a go between method like... assignType[T](cls: Class[T], t: AnyRef): T
        val mf = ClassManifest.classType(cls)
        classOp(mf)
    }
  }

  def classOp[T](implicit mf: ClassManifest[T]) {
    println("You specified a " + mf.erasure.getSimpleName)
  }

  class Bruce

}