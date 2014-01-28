package pkg.javainterop


object CallToJava {
  def main(args: Array[String]) {
    println("Want xyzxyz:" + JavaCls.twice("xyz"))

    val cls = classOf[JavaCls]
    val jc = cls.newInstance()
    println("Instantiated " + jc)
  }
}