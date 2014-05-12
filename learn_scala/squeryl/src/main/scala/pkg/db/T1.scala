package pkg.db

case class T1(id: Long,
              ver: Int,
              name: String,
              nicName: Option[String]) extends HasIdVer