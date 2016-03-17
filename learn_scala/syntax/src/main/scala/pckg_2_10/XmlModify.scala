package pckg_2_10


object XmlModify {

  def main(args: Array[String]) {
    val before = <root attr1="before" attr2="keep">
      <child>hello</child>
    </root>
    val after = before.copy(attributes = before.attributes.append(<alt attr1="after"/>.attributes))
    val exp = <root attr1="after" attr2="keep">
      <child>hello</child>
    </root>
    assert(after == exp)
  }
}