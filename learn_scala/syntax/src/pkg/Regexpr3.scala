package pkg

object Regexpr3 {
  val input = """A	1	d	d	d	d	d	-	-
B	2	a	a	a	a	a	-	-
C	3	d	d	d	d	d	-	-
D	4	a	a	a	a	a	-	-
E	5	d	d	d	-	-	N	N
F	6	n	-	-	d	d	D	D
G	7	-	n	n	n	n	-	-
"""

  def main(args: Array[String]) {
    val re = """(\S*)\s+(\d+)((?:\s+(?:d|D|a|n|N|s|S|-)){7})""".r
    for (line <- io.Source.fromString(input).getLines()) {
      println(line)
      val first = re.findFirstMatchIn(line)
      first match {
        case None => println("No match")
        case Some(m) =>
          require(m.groupCount == 3)
          println("Found crew: " + m.group(1) + ", Week: " + m.group(2) +
            ", Week pattern: " + m.group(3))
      }
    }
  }
}