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

  private def test(in: NodeSeq, selector: String, arg: NodeSeq, desc: String) = {
    val result = (selector #> arg)(in)
    TestItem(in, selector, arg, result, desc)
  }

  def main(args: Array[String]) {
    println(tests)
  }
}