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
      case e: AssertionError =>
        assert(e.getMessage.contains("mary"))
    }

  }

  /**
   * A reflective case class 'copy'.
   */
  def copy[T: ru.TypeTag : ClassTag](t: T, vals: (String, Any)*): T = {
    if (vals.isEmpty) return t

    // mirrors
    val tpe = ru.typeOf[T]
    val mir = ru.runtimeMirror(t.getClass.getClassLoader)
    val clsMir = mir.reflectClass(tpe.typeSymbol.asClass)
    val instMir = mir.reflect(t)

    // constructor
    // NOTE: This barfs when multiple contructors, however, for case classes
    //       one would overload the 'apply' method on companion anyway
    val ctor = tpe.declaration(ru.nme.CONSTRUCTOR).asMethod
    val ctorm = clsMir.reflectConstructor(ctor)

    // args
    var valMap = vals.toMap
    val args = tpe.declarations
      .filter(d => !d.isMethod)
      // ignores extra vals
      .take(ctor.paramss(0).size)
      .map {
      m =>
        val full = m.fullName
        val name = full.substring(full.lastIndexOf('.') + 1)
        val opt = valMap.get(name)
        if (opt.isDefined) {
          valMap = valMap - name
          opt.get
        }
        else instMir.reflectField(m.asTerm).get
    }
      .toSeq
    assert(valMap.isEmpty, "Unused values for: " + valMap.keys.mkString(","))
    ctorm(args: _*).asInstanceOf[T]
  }
}