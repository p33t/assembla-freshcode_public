package pkg.db

import org.squeryl.dsl._
import org.squeryl.{KeyedEntityDef, PrimitiveTypeMode}
import pkg.db.model.DirectionEnum

object DbTypes extends PrimitiveTypeMode {

  class HasIdDef[T <: HasId] extends KeyedEntityDef[T, Long] {
    def getId(a: T) = a.id

    def idPropertyName = "id"

    def isPersisted(a: T) = true
  }

  class HasIdVerDef[T <: HasIdVer] extends HasIdDef[T] {
    override def optimisticCounterPropertyName = Some("ver")
  }

  // Type Expression Factories ================================================================================
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


  implicit val directionTEF = new EnumMapper(DirectionEnum.NORTH)

  implicit def directionToTE(x: DirectionEnum) = directionTEF.create(x)

  implicit val optionDirectionTEF = new EnumTEF(directionTEF)

  implicit def optionDirectionToTE(x: Option[DirectionEnum]) = optionDirectionTEF.create(x)


  // Enum handling ===========================================================================================

  class EnumMapper[T >: Null <: Enum[T]](override val sample: T) extends NonPrimitiveJdbcMapper[String, T, TString](stringTEF, this) {
    override def convertFromJdbc(v: String) = nullAlt(v, Enum.valueOf(sample.getDeclaringClass, v))

    override def convertToJdbc(v: T) = nullAlt(v, v.name())

    override def defaultColumnLength = 32
  }

  class EnumTEF[T >: Null <: Enum[T]](override val deOptionizer: EnumMapper[T])
    extends TypedExpressionFactory[Option[T], TString] with DeOptionizer[String, T, TString, Option[T], TString]


  private def nullAlt[T, U](t: T, whenNotNull: => U): U = if (t == null) null.asInstanceOf[U] else whenNotNull
}