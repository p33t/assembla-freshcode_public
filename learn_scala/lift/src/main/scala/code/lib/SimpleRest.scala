package code.lib

import extjsinterop.{NumStrMap, NumStr}
import net.liftweb._
import http.rest.RestHelper
import http.{LiftResponse, BadResponse, NotFoundResponse, OkResponse}
import json._
import common._
import util._
import BasicTypesHelpers._
import net.liftweb.json.{Extraction, JsonAST}
import Extraction._

/**
 * A very simple REST service.  There is a SoapUI project in the base folder that tests this.
 */
object SimpleRest extends RestHelper {

  val db = new NumStrMap

  serve("extjsinterop" / "restelem" prefix {
    // list all items
    case Nil JsonGet _ => decompose(db.values())

    // /api/item/item_id gets the specified item (or a 404)
    case AsInt(id) :: Nil JsonGet _ => retrieveAndProcess(id, decompose(_))

    // DELETE the item in question
    case AsInt(id) :: Nil JsonDelete _ =>
      val removed = db.remove(id)
      if (removed.isDefined) decompose(removed.get)
      else notFound(id)

    // PUT adds the item if the JSON is parsable
    case Nil JsonPut (obj: JObject) -> _ =>
      // ID is supplied by server and need to happen before extract in case it is not supplied
      // NOTE: This is vulnerable to id draining
      val json = assignId(obj, db.nextId())
      val opt = extractOpt[NumStr](json)
      opt match {
        case None =>
          BadResponse()
        case Some(elem) =>
          db.put(elem)
          decompose(elem)
      }

    // POST if we find the item, merge the fields from the
    // the POST body and update the item
    case AsInt(id) :: Nil JsonPost jv -> _ =>
      retrieveAndProcess(id, {
        elem =>
        val orig = decompose(elem)
        val newJv = mergeJson(orig, jv)
        val opt = extractOpt[NumStr](newJv)
        opt match {
          case Some(newElem) =>
            val correctId = newElem.copy(id = id) // make sure ID has not changed
            db.put(correctId)
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

  private def assignId(obj: JObject, id: Int) = {
    val jid = JInt(id)
    if (obj.obj.find(_.name == "id").isDefined) obj.replace("id" :: Nil, jid)
    else JObject(JField("id", jid) :: obj.obj)
  }

  private def retrieveAndProcess(id: Int, fn: NumStr => LiftResponse): LiftResponse = {
    val opt = db.get(id)
    if (opt.isDefined) fn(opt.get)
    else notFound(id)
  }

  private def notFound(id: Int) = {
    NotFoundResponse("Unable to locate id " + id)
  }
}