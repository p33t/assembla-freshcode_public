package pkg.db

import org.squeryl.{KeyedEntityDef, PrimitiveTypeMode}
import org.squeryl.dsl._

object DbTypes extends PrimitiveTypeMode {

  class HasIdDef[T <: HasId] extends KeyedEntityDef[T, Long] {
    def getId(a: T) = a.id

    def idPropertyName = "id"

    def isPersisted(a: T) = true
  }

  class HasIdVerDef[T <: HasIdVer] extends HasIdDef[T] {
    override def optimisticCounterPropertyName = Some("ver")
  }

  // Type Expresion Factories ================================================================================
  implicit val t1TEF = new HasIdVerDef[T1]
  implicit val customTEF = new HasIdDef[Custom]

  // Rev String mapping ======================================================================================

  implicit val revStringTEF = new NonPrimitiveJdbcMapper[String, RevString, TString](stringTEF, this) {
    def convertFromJdbc(s: String) = new RevString(s.reverse)

    def convertToJdbc(r: RevString) = r.str.reverse
  }

  implicit val optionRevStringTEF = new TypedExpressionFactory[Option[RevString], TOptionString]
    with DeOptionizer[String, RevString, TString, Option[RevString], TOptionString] {
    val deOptionizer = revStringTEF
  }

  implicit def revStringToTE(r: RevString) = revStringTEF.create(r)

  implicit def optionRevStringToTE(r: Option[RevString]) = optionRevStringTEF.create(r)
}