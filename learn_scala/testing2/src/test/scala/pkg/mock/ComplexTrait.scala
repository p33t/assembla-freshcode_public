package pkg.mock


trait ComplexTrait {
  val procStr: String => String
  val procIter: Iterable[String] => Int


  def apply(str: String) = procStr(str)

  def alt(ss: Iterable[String]) = procIter(ss)
}