package pkg.javainterop


object CallToJava {
  def main(args: Array[String]) {
    println("Want xyzxyz:" + JavaCls.twice("xyz"))
  }
}