package pkg


object TypeInObject {
  type TypeInObject = String

  def someOperation(tio: TypeInObject) {
    println(tio)
  }

  def main(args: Array[String]) {
    TypeInObject.someOperation("Hello This Works")
  }

}