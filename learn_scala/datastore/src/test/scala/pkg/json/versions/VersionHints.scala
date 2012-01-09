package pkg.json.versions

import net.liftweb.json.TypeHints

/**
 * Piggyback versions on type hints mechanism.  This means an absent hint results in the current version (?).
 */
object VersionHints extends TypeHints {
  private val ClassVersions = Map("v1" -> classOf[MyModel1], "v2" -> classOf[MyModel])

  val hints: List[Class[_]] = ClassVersions.values.toList

  // NOTE: This is probably quite inefficient
  def hintFor(clazz: Class[_]): String = ClassVersions.find(clazz == _._2).get._1

  def classFor(hint: String): Option[Class[_]] = ClassVersions.get(hint)
}