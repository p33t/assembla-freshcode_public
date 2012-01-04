package bootstrap.liftweb

import net.liftweb.sitemap.Menu.{WithSlash, Menuable}
import java.util.UUID
import net.liftweb.sitemap.LocPath._
import net.liftweb.sitemap.{Menu, ConvertableToMenu}

object FancyMenu {
  def apply() = {
    type FubarType = Menuable with WithSlash

    def appendPath(pm: FubarType, path: List[String]): FubarType = {
      path.foldLeft(pm) {
        (soFar, elem) =>
          soFar / elem
      }
    }
    val fancyData = List("0/0/0", "0/1/0", "0/1/1", "1/0", "2", "2/0")
    val paths = fancyData.map(_.split("/").toList)

    // Use the path of the first non-generated node
    def altPath(n: StringNode): List[String] = {
      val path = n.path
      if (paths.contains(path)) path
      else {
        require(!n.children.isEmpty, n.path + " is empty")
        altPath(n.children(0))
      }
    }

    def menuFor(n: StringNode): ConvertableToMenu = {
      val target = altPath(n)
      val label = if (n.isRoot) "Fancy Menus" else n.name
      val linkName = label + "_" + UUID.randomUUID() // the name needs to be unique
      val menu = Menu(linkName, label)
      // This technique seems to cause problems.
//      val item = menu / "experiments" / "fancy_menus_by_name" / target.mkString("/")
            val item = appendPath(menu / "experiments" / "fancy_menus_by_name", target)
//      println("Path labelled '" + label + "' added " + item.path)
      if (!n.children.isEmpty) {
        val subs = n.children.toList.map(menuFor(_))
        item.submenus(subs)
      }
      else item
    }

    val root = StringNode()
    paths.map(root.ensurePath(_))
    menuFor(root)
  }
}