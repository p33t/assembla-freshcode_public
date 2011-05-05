package code.lib

import net.liftweb._
import common.Full
import http._
import json.Extraction

object MyStatelessDispatch {
  val formats = net.liftweb.json.DefaultFormats

  case class Info(fileName: String, suffix: String)

  def response(info: Info) = {
    () => Full(JsonResponse(Extraction.decompose(info)(formats)))
  }

  def dispatchThis: LiftRules.DispatchPF = {
    case Req("stateless" :: fileName :: Nil, suffix, GetRequest) => response(Info(fileName, suffix))
    case Req("stateless" :: fileName1 :: fileName2 :: Nil, suffix, GetRequest) => response(Info(fileName1 + "/" + fileName2, suffix))
  }
}