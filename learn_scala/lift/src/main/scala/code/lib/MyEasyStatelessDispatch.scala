package code.lib

import net.liftweb._
import common.Empty
import http._
import rest._
import json._
import scala.xml._


object MyEasyStatelessDispatch extends RestHelper {
  implicit def rhymeToResponseByAccepts: JxCvtPF[Rhyme] = {
    case (JsonSelect, r, _) => r: JValue
    case (XmlSelect, r, _) => r: Node
  }

  /*Notes:
    - Post/Put have different pattern match signatures (EG: (List[String],(JValue,Req))
    - Can do async on server using 'RestContinuation.async'.  Can schedule a timeout default response.
  */

  // Auto switch between XML / JSON
  serveJx[Rhyme] {
    "stateless-easy" / "blah" prefixJx {
      case Get(Nil, _) => Empty
      case Get(list, Req(_, suffix, _)) => {
        Rhyme(suffix, list)
      }
    }
  }
}

case class Rhyme(ext: String, words: List[String])

object Rhyme {
  private implicit val formats = net.liftweb.json.DefaultFormats

  implicit def toXml(r: Rhyme): Node =
    <item>
      {Xml.toXml(r)}
    </item>

  implicit def toJson(r: Rhyme): JValue =
    Extraction.decompose(r)
}