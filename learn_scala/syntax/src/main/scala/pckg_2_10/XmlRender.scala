package pckg_2_10

/**
 * Not sure how to influence auto code formatting... and this is pretty ugly.
 */
object XmlRender {
  def main(args: Array[String]) {
    val headers = List("1st", "2nd", "3rd")
    val rows = List(
      List("a", "b", "c"),
      List("d", "e", "f")
    )

    val xml = <table>
      <tr>
        {headerRender(headers)}
      </tr>{rowRender(rows)}
    </table>
    println(xml)
  }

  def rowRender(rows: List[List[String]]) = {
    rows.map(r => <tr>
      {r.map(d => <td>d</td>)}
    </tr>)
  }

  def headerRender(headers: List[String]) = {
    headers.map(h => th(h))
  }

  def th(h: String) = {
    <th>
      {h}
    </th>
  }
}
