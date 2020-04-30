package pkg1

/**
 * AKA Duck Typing.  Can do combination of common ancestor AND defined methods.
 */
object StructuralType {

  class A

  class B extends A {
    def method() {
      println("B.method called")
    }
  }

  class C extends A {
    def method() {
      println("C.method called")
    }
  }

  // a structural type requiring an object that has a 'method' method
  def callMethod(m: {def method(): Unit}) {
    m.method()
  }

  // a structural type that has a common ancestor
  def callAMethod(m: A {def method(): Unit}) {
    m.method()
  }

  def main(args: Array[String]) {
    //    Doesn't compile...
    //    callMethod(new A)
    callMethod(new B)
    callMethod(new C)
    callAMethod(new B)
    callAMethod(new C)
  }
}