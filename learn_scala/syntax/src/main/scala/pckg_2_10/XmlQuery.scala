package pckg_2_10


object XmlQuery {
  val root =
    <root>
      <a>
        <b x="unwant">a1b1</b>
        <b x="want">a1b2</b>
      </a>
      <a>
        <b>a2b1</b>
        <b>a2b2</b>
      </a>
    </root>

  def main(args: Array[String]) {
    val x = root \ "a" \ "b"
    assert(x.map(_.text) == List("a1b1", "a1b2", "a2b1", "a2b2"))

    val y = root \\ "b"
    assert(y.map(_.text) == List("a1b1", "a1b2", "a2b1", "a2b2"))

    val z = root \\ "c"
    assert(z.isEmpty)

    val sub = root \ "a" \ "b" filter (_.attributes("x").exists(_.text == "want"))
    assert(sub == <b x="want">a1b2</b>)
  }
}