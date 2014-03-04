package pkg.mock


trait ComplexTrait {
  val procStr: String => String


  def apply(str: String) = procStr(str)
}