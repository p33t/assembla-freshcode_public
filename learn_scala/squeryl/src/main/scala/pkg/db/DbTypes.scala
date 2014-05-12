package pkg.db

import org.squeryl.PrimitiveTypeMode
import org.squeryl.dsl._


object DbTypes extends PrimitiveTypeMode {

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