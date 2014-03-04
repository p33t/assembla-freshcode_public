package pkg.mock


trait ComplexTrait {
  val procStr: String => String
  val procIter: List[String] => Int


  def apply(str: String) = procStr(str)

  def alt(ss: List[String]) = procIter(ss)
}