package code.snippet

import net.liftweb.util._
import Helpers._
import xml._
import NodeSeq.Empty
import util.Random

object TablePopulate {
  val blank: Option[String] = None
  val removeIds = "* [id]" #> blank
  val tidyUp = ClearClearable & removeIds
  val Days = "SMTWTFS"
  val Crews = "ABCDE"
  val WeeksPerCrew = 2
  val Rows = Range(1, (Crews.size * WeeksPerCrew) + 1)
  val Shifts = "ADN--" // Hmmm... it appears the parameter to Random.nextInt(Int) is actually exclusive.

  // TODO: Figure out 'memoise'
  def render(in: NodeSeq): NodeSeq = {
    val headerTemplate = (".headerRow ^^" #> "")(in)
    val headerDays = Days.foldLeft(Empty) {(xml, day) => xml ++ <th>{day}</th>}
    val header = (".insertHere" #> headerDays &
      tidyUp)(headerTemplate)

    val rowTemplate = ("#sampleRow ^^" #> "")(in)
    def rowContent(row: Int) = {
      val shiftCells = Days.foldLeft(Empty) {(xml, day) => xml ++ <td>{randomShift}</td>}
      ("#sampleCrewCell *" #> crew(row) &
        "#sampleWeekCell *" #> row &
        "#sampleHoursCell *" #> Random.nextInt(99) &
        ".insertHere" #> shiftCells &
        tidyUp)(rowTemplate)
    }

    val body = Rows.foldLeft(Empty) {(xml, row) => xml ++ rowContent(row)}
    (".rosterTable *" #> (header ++ body))(in)
  }

  private def crew(row: Int) = {
    if (row % WeeksPerCrew == 0) ""
    else Crews(row / WeeksPerCrew).toString
  }

  private def randomShift: Char = {
    Shifts(Random.nextInt(Shifts.size - 1))
  }
}
