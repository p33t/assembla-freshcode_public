package pckg_2_10

import pckg_2_10.fixture.FancyCaseClass
import java.lang.reflect.Modifier

/**
 * Copying a case class using Java reflection to avoid generics.
 */
object CaseClassCopy {
  def main(args: Array[String]) {
    val o = FancyCaseClass("shiney", 9)
    println(o + " ===> " + copy(o, "str2" -> "shoes", "i" -> 99))

    try {
      copy(o, "qwerty" -> "bad")
      throw new IllegalStateException("Should have popped")
    }
    catch {
      case e: IllegalArgumentException =>
        assert(e.getMessage.contains("qwerty"))
    }
  }

  def copy(o: AnyRef, vals: (String, Any)*) = {
    val copier = new Copier(o.getClass)
    copier(o, vals: _*)
  }

  /**
   * Utility class for providing copying of a designated case class with minimal overhead.
   */
  class Copier(cls: Class[_]) {
    private val ctor = cls.getConstructors.apply(0)
    private val getters = cls.getDeclaredFields
      .filter {
      f =>
        val m = f.getModifiers
        Modifier.isPrivate(m) && Modifier.isFinal(m) && !Modifier.isStatic(m)
    }
      .take(ctor.getParameterTypes.size)
      .map(f => cls.getMethod(f.getName))

    /**
     * A reflective, non-generic version of case class copying.
     */
    def apply[T](o: T, vals: (String, Any)*): T = {
      val byIx = vals.map {
        case (name, value) =>
          val ix = getters.indexWhere(_.getName == name)
          if (ix < 0) throw new IllegalArgumentException("Unknown field: " + name)
          (ix, value.asInstanceOf[Object])
      }.toMap

      val args = (0 until getters.size).map {
        i =>
          byIx.get(i)
            .getOrElse(getters(i).invoke(o))
      }
      ctor.newInstance(args: _*).asInstanceOf[T]
    }
  }

}