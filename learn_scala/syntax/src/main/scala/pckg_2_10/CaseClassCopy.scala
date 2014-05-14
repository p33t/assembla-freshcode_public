package pckg_2_10

import pckg_2_10.fixture.{FancyCaseClass, SomeCaseClass}
import java.lang.reflect.Modifier
import scala.reflect.runtime.{universe => ru}
import scala.reflect.ClassTag

object CaseClassCopy {
  val DesiredModifier = (i: Int) => Modifier.isPrivate(i) && Modifier.isFinal(i) && !Modifier.isStatic(i)

  def main(args: Array[String]) {
    println()
    val scc = SomeCaseClass("xyz")
    println(scc + " ===> " + copy(scc, "str" -> "abc"))

    println()
    val fcc = FancyCaseClass("bruce", 1, 2, "lee")
    println(fcc + " ===> " + copy(fcc, "str2" -> "Springsteen", "i" -> 99))

    copy(fcc, "mary" -> "had a little lamb")
  }

  def copy[T: ru.TypeTag : ClassTag](t: T, vals: (String, Any)*) = {
    // mirrors
    val tpe = ru.typeOf[T]
    val mir = ru.runtimeMirror(t.getClass.getClassLoader)
    val clsMir = mir.reflectClass(tpe.typeSymbol.asClass)
    val instMir = mir.reflect(t)

    // args
    var valMap = vals.toMap
    val args = tpe.declarations
      .filter(d => !d.isMethod)
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

    // constructor
    val ctor = tpe.declaration(ru.nme.CONSTRUCTOR).asMethod
    val ctorm = clsMir.reflectConstructor(ctor)
    ctorm(args: _*)
  }
}