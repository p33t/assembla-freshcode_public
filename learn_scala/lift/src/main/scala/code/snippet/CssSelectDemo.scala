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

  case class TestItem(in: NodeSeq, selector: String, arg: NodeSeq, result: NodeSeq)

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
        <th>Input</th> <th>Selector</th> <th>Param</th> <th>Result</th>
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
    test(<a attr="attrVal">
        <b/>
    </a>, "*", <c/>) ::
      Nil
  }

  private def test(in: NodeSeq, selector: String, arg: NodeSeq) = {
    val result = (selector #> arg)(in)
    TestItem(in, selector, arg, result)
  }

  def main(args: Array[String]) {
    val para = <p>Some text in a paragraph</p>
    val aDiv = <div class="someclass">
      {para}
    </div>
    println(tests)
  }
}