package pkg1

object NoParams {

  //noinspection UnitMethodIsParameterless
  def noParams {
    println("No Params")
  }

  def emptyParams() {
    println("Empty Params")
  }

  def twoEmptyParams()() {
    println("Two Empty Params")
  }

  def main(args: Array[String]) {
    //Doesn't compile..
//    noParams()
    noParams
    emptyParams // Still works but not good
    emptyParams()
    twoEmptyParams
    twoEmptyParams()
    twoEmptyParams()()

    val f1 = noParams _
    // Does not invoke..
    // f1
    f1()

    // does not compile..
    // val f2 = noParams(_)
    
    val f3 = emptyParams _
    f3()

    // does not compile...
    // val f4 = emptyParams(_)

    val f5 = twoEmptyParams _
    f5() // does not invoke !!!!!!
    f5()()
  }
}