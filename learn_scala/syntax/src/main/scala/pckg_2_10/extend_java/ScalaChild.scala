package pckg_2_10.extend_java


class ScalaChild extends JavaParent {
  // IDE marks this red and reports "overrides nothing" however it compiles.
  // Using "action: Action[_ <: Result]" prevents compile.
  override def execute(action: Action[_]): Result = {
    super.execute(action.asInstanceOf[Action[_ <: Result]])
  }
}