package code.lib

import net.liftweb._
import http.rest.RestHelper
import http.{LiftResponse, BadResponse, NotFoundResponse, OkResponse}
import json._
import common._
import util._
import BasicTypesHelpers._
import net.liftweb.json.{Extraction, JsonAST}
import Extraction._


object SimpleRest extends RestHelper {

  // NOTE: Horribly non-thread safe
  var elems = Map[Int, RestElem](1 -> RestElem(1, 10, "ten"))

  // NOTE: Horribly non-thread safe
  var prevId = 1

  def nextId() = {
    prevId = prevId + 1
    prevId
  }

  serve("extjsinterop" / "restelem" prefix {
    // list all items
    case Nil JsonGet _ => decompose(elems.values)

    // /api/item/item_id gets the specified item (or a 404)
    case AsInt(id) :: Nil JsonGet _ => retrieveAndProcess(id, decompose(_))

    // DELETE the item in question
    case AsInt(id) :: Nil JsonDelete _ =>
      retrieveAndProcess(id, {
        elem =>
          elems = elems - id
          decompose(elem)
      })

    // PUT adds the item if the JSON is parsable
    case Nil JsonPut jv -> _ =>
      // ID is supplied by server and need to happen before extract in case it is not supplied
      val json = mergeJson(jv, JObject(List(JField("id", JInt(nextId())))))
      val opt = extractOpt[RestElem](json)
      opt match {
        case None =>
          BadResponse()
        case Some(elem) =>
          putElem(elem)
          decompose(elem)
      }


    // POST if we find the item, merge the fields from the
    // the POST body and update the item
    case AsInt(id) :: Nil JsonPost jv -> _ =>
      retrieveAndProcess(id, {
        elem =>
        val orig = decompose(elem)
        val newJv = mergeJson(orig, jv)
        val opt = extractOpt[RestElem](newJv)
        opt match {
          case Some(newElem) =>
            val correctId = newElem.copy(id = id) // make sure ID has not changed
            putElem(correctId)
            OkResponse()
          case None =>
            BadResponse()
        }
      })

    /*
    // /api/item returns all the items
    ​
        // /api/item/count gets the item count
        case "count" :: Nil JsonGet _ => JInt(Item.inventoryItems.length)
    ​
    ​
        // /api/item/search/foo or /api/item/search?q=foo
        case "search" :: q JsonGet _ =>
          (for {
            searchString <- q ::: S.params("q")
            item <- Item.search(searchString)
          } yield item).distinct: JValue
    ​    ​
    */
  })

  private def putElem(elem: SimpleRest.RestElem) {
    val t2 = elem.id -> elem
    elems = elems + t2
  }

  private def retrieveAndProcess(id: Int, fn: RestElem => LiftResponse): LiftResponse = {
    val opt = elems.get(id)
    if (opt.isDefined) fn(opt.get)
    else NotFoundResponse("Unable to locate id " + id)
  }

  case class RestElem(id: Int, num: Int, str: String)

  //  object RestElem {
  //    val Blank = RestElem(0, 0, "")
  //    val BlankJson = decompose(Blank)
  //  }

}