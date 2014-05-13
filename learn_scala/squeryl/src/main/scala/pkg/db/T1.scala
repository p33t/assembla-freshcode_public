package pkg.db

case class T1(id: Int,
              ver: Int,
              name: String,
              nicName: Option[String]) extends HasIdVer