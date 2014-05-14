package pckg_2_10

import pckg_2_10.fixture.{FancyCaseClass, SomeCaseClass}
import scala.reflect.runtime.{universe => ru}
import scala.reflect.ClassTag

object CaseClassCopy {
  def main(args: Array[String]) {
    println()
    val scc = SomeCaseClass("xyz")
    println(scc + " ===> " + copy(scc, "str" -> "abc"))

    println()
    val fcc = FancyCaseClass("bruce", 1)
    println(fcc + " ===> " + copy(fcc, "str2" -> "Springsteen", "i" -> 99))

    try {
      copy(fcc, "mary" -> "had a little lamb")
      throw new IllegalStateException("Should never get here")
    }
    catch {
      case e: IllegalArgumentException =>
        assert(e.getMessage.contains("mary"))
    }
  }

  /**
   * A reflective case class 'copy'.
   */
  def copy[T: ru.TypeTag : ClassTag](t: T, vals: (String, Any)*): T = {
    val copier = new Copier[T]()
    copier(t, vals: _*)
  }

  /**
   * Caches meta-info about copying of a particular class for speed.
   */
  class Copier[T: ru.TypeTag : ClassTag]() {
    private val tpe = ru.typeOf[T]
    private val mir = ru.runtimeMirror(manifest[T].runtimeClass.getClassLoader)
    private val ctorm = {
      val clsMir = mir.reflectClass(tpe.typeSymbol.asClass)
      // NOTE: This barfs when multiple contructors, however, for case classes
      //       one would overload the 'apply' method on companion anyway
      val ctor = tpe.declaration(ru.nme.CONSTRUCTOR).asMethod
      clsMir.reflectConstructor(ctor)
    }
    private val terms = tpe.declarations
      .filter(d => !d.isMethod)
      // ignores extra vals
      .take(ctorm.symbol.paramss(0).size)
      .map(_.asTerm)
      .toList
    private val ixs = 0 until terms.size
    private val argOrder = ixs.map {
      i =>
        val term = terms(i)
        val full = term.fullName
        val name = full.substring(full.lastIndexOf('.') + 1)
        (name, i)
    }.toMap

    /**
     * A reflective copying of a case class.
     */
    def apply(t: T, vals: (String, Any)*): T = {
      if (vals.isEmpty) return t

      // Check name and convert to argIx
      val valByIx = vals.map {
        case (name, newVal) =>
          val ix = argOrder.get(name)
            .getOrElse(throw new IllegalArgumentException("Unknown field: " + name))
          (ix, newVal)
      }.toMap

      lazy val instMir = mir.reflect(t)
      val args = ixs.map {
        i =>
          valByIx.get(i)
            .getOrElse(instMir.reflectField(terms(i)).get)
      }
      ctorm(args: _*).asInstanceOf[T]
    }
  }

}