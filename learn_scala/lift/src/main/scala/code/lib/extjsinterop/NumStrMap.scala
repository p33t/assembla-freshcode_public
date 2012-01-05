package code.lib.extjsinterop


class NumStrMap {
  // NOTE: Horribly non-thread safe
  var elems = Map[Int, NumStr](1 -> NumStr(1, 10, "ten")) // some default data

  // NOTE: Horribly non-thread safe
  private var prevId = 1

  def nextId() = {
    prevId = prevId + 1
    prevId
  }

  def put(elem: NumStr) {
    val t2 = elem.id -> elem
    elems = elems + t2
  }

  def remove(id: Int): Option[NumStr] = {
    val opt = elems.get(id)
    if (opt.isDefined) elems = elems - id
    opt
  }

  def values() = elems.values

  def get(id: Int) = elems.get(id)
}
