package code.snippet

import net.liftweb._
import http._
import js._
import JsCmds._
import util._
import Helpers._

import net.liftweb.http.js.jquery.JqWiringSupport
import net.liftweb.http.{WiringUI, SHtml}

object FormCellWireAjax {
  val theCell = ValueCell("Not Set")

  def updateForm = {
    var newValue = ""
    def process: JsCmd = {
      // pause for effect
      Thread.sleep(500)

      theCell.set(newValue)
      Noop;
    }

    "name=newValue" #> (SHtml.text(newValue, newValue = _) ++ SHtml.hidden(process _))
  }

  def outputCell = {
    "*" #> WiringUI.asText(theCell, JqWiringSupport.fade)
  }
}