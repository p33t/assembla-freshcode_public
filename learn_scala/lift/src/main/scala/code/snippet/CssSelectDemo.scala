package code.snippet

import net.liftweb.util._
import Helpers._
import xml._

object CssSelectDemo {
  private val pretty = new PrettyPrinter(80, 2)

  def echo(in: NodeSeq): NodeSeq = {
    in ++ <pre>
      {Text(in.toString())}
    </pre>
  }

  case class TestItem(in: NodeSeq, selector: String, arg: NodeSeq, result: NodeSeq, desc: String)

  def table(in: NodeSeq): NodeSeq = {
    def nsCell(ns: NodeSeq) = {
      // Trying to prevent extra whitespace inside a 'pre'
      val pre = ("* *" #> Text(pretty.formatNodes(ns)))(<pre/>)
      <td>
        {pre}
      </td>
    }

    <table border="1">
      <tr>
        <th>Input</th> <th>Selector</th> <th>Arg</th> <th>Result</th>
      </tr>{tests.map {
      t: TestItem =>
        <tr>
          {nsCell(t.in)}<td>
          {Text(t.selector)}
        </td>{nsCell(t.arg)}{nsCell(t.result)}
        </tr>
    }}
    </table>
  }

  private def tests: Seq[TestItem] = {
    val in = <a attr="attrVal">
        <b1 attr="b1attr"/>
        <b2 attr="b2attr"/>
    </a>
    test(in, "*", <c/>, "Retain top attributes only") ::
      test(in, "* *", <c/>, "Replace children") ::
      test(in, "* ^*", Text("Doesn't matter"), "Elevate children (arg ignored)") ::
      test(in, "* ^^", Text("Doesn't matter"), "Noop (arg ignored)") ::
      Nil
  }

  def run(in: NodeSeq, selector: String, arg: NodeSeq): NodeSeq = {
    (selector #> arg)(in)
  }

  private def test(in: NodeSeq, selector: String, arg: NodeSeq, desc: String) = {
    val result = run(in, selector, arg)
    TestItem(in, selector, arg, result, desc)
  }

  def main(args: Array[String]) {
    println(tests)

    println("Compound?")
    val in = <a1 a1-attr="a1-attr">
        <a1b1 a1b1-attr="a1b1-attr"/>
    </a1> ++ 
      <a2 a2-attr="a2-attr">
          <a2b1 a2b1-attr="a2b1-attr"/>
      </a2>

    // Hmm... this appears to use only the first element's children
    // Basically it assumes only one child and mangles if it is not
    def bonusAttribOnChildren(in: NodeSeq): NodeSeq = {
      val children = ("* ^*" #> NodeSeq.Empty)(in)
      val altChildren = ("* [class+]" #> "bonus-class")(children)
      ("* *" #> altChildren)(in)
    }

    val out = bonusAttribOnChildren(in)
    println(pretty.formatNodes(out))
  }
}