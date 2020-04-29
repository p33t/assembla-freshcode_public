package pkg1

import scala.reflect.ClassManifest

/**
 * Exploring manifest.  This illustration is superseeded by [[pkg2.TypeArgDemo]].
 */
@deprecated(message="Superceeded by TypeArgDemo")
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

    // Thats more like it.
    classOp(manifest[Bruce])
    classOpBetter[Bruce]()
  }

  def classOp[T](implicit mf: ClassManifest[T]) {
    println("You specified a " + mf.erasure.getSimpleName)
  }

  /**
   * This technique was learned from subcut 1.0 and does away with the implicit parameter list.
   */
  def classOpBetter[T <: Any : Manifest]() {
    classOp[T]
  }

  class Bruce

}