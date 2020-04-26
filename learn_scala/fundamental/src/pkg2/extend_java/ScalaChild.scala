package pkg2.extend_java

class ScalaChild extends JavaParent {
  override def execute(action: Action[_]): Result = {
    super.execute(action.asInstanceOf[Action[_]])
  }
}

object ScalaChild {
  def main(args: Array[String]): Unit = {
    val child = new ScalaChild();
    val actual = child.execute(new Action[Result] {
    })
    println("Expecting 'null': " + actual);
  }
}