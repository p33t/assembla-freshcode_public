package code.lib

import net.liftweb._
import http.rest.RestHelper
import http.{BadResponse, NotFoundResponse, OkResponse}
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
  var prevId = 2
  def nextId() = {
    prevId = prevId + 1
    prevId
  }

  serve("extjsinterop" / "restelem" prefix {
    // list all items
    case Nil JsonGet _ => decompose(elems.values)

    // /api/item/item_id gets the specified item (or a 404)
    case AsInt(id) :: Nil JsonGet _ => {
      val opt = elems.get(id)
      if (opt.isDefined) decompose(opt.get)
      else NotFoundResponse("Unable to locate id " + id)
    }

    // DELETE the item in question
    case AsInt(id) :: Nil JsonDelete _ =>
      // TODO: Remove dupe with id lookup (?!)
      val opt = elems.get(id)
      if (opt.isDefined) {
        elems = elems - id
        decompose(opt.get)
      }
      else NotFoundResponse("Unable to locate id " + id)

    // TODO: Untested
    // PUT adds the item if the JSON is parsable
    case Nil JsonPut (obj: JObject) => {
      // suppliment with an ID 0 if necessary
      val json = mergeJson(JObject(List(JField("id", JInt(0)))), obj)
      val opt = extractOpt[RestElem](json)
      opt match {
        case None =>
          BadResponse()
        case Some(elem) =>
          val newElem = elem.copy(id = nextId())
          val t2 = newElem.id -> newElem
          elems = elems + t2
          decompose(newElem)
      }
    }

    // TODO: Post for updates

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

        // POST if we find the item, merge the fields from the
        // the POST body and update the item
        case Item(item) :: Nil JsonPost json -> _ =>
          Item(mergeJson(item, json)).map(Item.add(_): JValue)

    */
  })

  case class RestElem(id: Int, num: Int, str: String)
//  object RestElem {
//    val Blank = RestElem(0, 0, "")
//    val BlankJson = decompose(Blank)
//  }

}