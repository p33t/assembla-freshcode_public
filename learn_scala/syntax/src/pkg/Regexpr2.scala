package pkg

import collection.mutable.ListBuffer


object Regexpr2 {
  //  Task: Successfully identify parts of this text...
  //    <header>....<body-elem-1>.....<body-elem-2>...  <header>...<body-elem-1>.....<body-elem-2>...

  def matches(s: String): List[(String, String, String)] = {
    val expr = """<header>(.*?)<body-elem-1>(.*?)<body-elem-2>(.*?)(<header>|\z)""".r
    val accum = new ListBuffer[(String,String,String)]

    def findMatches(str: String) {
      val first = expr.findFirstMatchIn(str)
      first match {
        case None => //println("no match on " + str)
        case Some(m) => {
          require(m.groupCount == 4)
          val found = (m.group(1), m.group(2), m.group(3))
          accum += found
          val rest = m.group(4) + str.substring(m.end)
          if (!rest.isEmpty) findMatches(rest)
        }
      }
    }
    findMatches(s)
    accum.toList
  }

  def test(s: String, expected: (String, String, String)*) {
    val actual = matches(s)
    val exp = List(expected: _*)
    require(actual == exp, "expected: " + exp + "\n actual: " + actual)
  }

  def main(args: Array[String]) {
    val in1 = "<header>bruce lee<body-elem-1>was here<body-elem-2>and kicked azz"
    val out1 = ("bruce lee", "was here", "and kicked azz")
    test("garbage" + in1, out1)
    test("garbage" + in1 * 2, out1, out1)
  }

}