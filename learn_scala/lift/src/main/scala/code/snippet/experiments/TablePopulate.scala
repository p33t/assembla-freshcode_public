package code.snippet.experiments

import net.liftweb.util._
import Helpers._
import xml._
import util.Random

object TablePopulate {
  val Blank: Option[String] = None
  val RemoveIds = "* [id]" #> Blank
  val TidyUp = ClearClearable & RemoveIds
  val Days = "SMTWTFS"
  val Crews = "ABCDE"
  val WeeksPerCrew = 2
  val Rows = Range(1, (Crews.size * WeeksPerCrew) + 1)
  val Shifts = "ADN--" // Hmmm... it appears the parameter to Random.nextInt(Int) is actually exclusive.

  // TODO: Figure out SHtml.memoize
  def render(in: NodeSeq): NodeSeq = {
    val headerTemplate = (".headerRow ^^" #> "")(in)
    val headerDays = Days.map {day => <th>{day}</th>}
    val header = (".insertHere" #> headerDays & TidyUp)(headerTemplate)

    val rowTemplate = ("#sampleRow ^^" #> "")(in)
    def rowContent(row: Int) = {
      val shiftCells = Days.map {_ => <td> {randomShift} </td>}
      ("#sampleCrewCell *" #> crew(row) &
        "#sampleWeekCell *" #> row &
        "#sampleHoursCell *" #> Random.nextInt(99) &
        ".insertHere" #> shiftCells &
        TidyUp)(rowTemplate)
    }

    // NOTE: For some reason, using 'map' here doesn't work.  The implicit casting doesn't work.
    // val body: NodeSeq = Rows.map(row => rowContent(row)).toList
    val body = Rows.map(rowContent).reduceLeft(_ ++ _)
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
