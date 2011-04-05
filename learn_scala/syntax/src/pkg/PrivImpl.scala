package pkg

trait PrivImpl {
  val s:String
}

object PrivImpl {
  def apply(j:Int):PrivImpl = {
    new MyPrivImpl(j.toString)
  }

  private class MyPrivImpl(val s:String) extends PrivImpl
}
