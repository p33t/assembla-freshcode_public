package pkg1

/**
 * Prove the type hierarchies of Null, AnyRef, Unit, AnyVal, Any, Nothing
 */
object TypeHierarchy {
  def main(args: Array[String]) {
    // null has only one instance and is a subclass of all AnyRef
    val nul: Null = null
    val anyRef: AnyRef = nul
    // Unit has only one instance and is a subclass of all AnyVal
    val unit: Unit = ()
    val anyVal: AnyVal = unit
    // Any is the parent class of AnyVal and AnyRef
    var any: Any = anyVal
    any = anyRef
    val str = {
      if (any == null) "any is null"
      else pop("any is supposed to be null")
    }
    println(str)
  }

  // Nothing has NO instances and is a subclass of all Any
  // It is used as the return type for methods that don't return normally
  // and will not interfere with type inferencing
  def pop(msg: String): Nothing = {
    throw new Exception(msg)
  }
}