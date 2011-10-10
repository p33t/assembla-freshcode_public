package bootstrap.liftweb

import net.liftweb._
import common.{Loggable, Full}
import http.RewriteResponse._
import sitemap._
import sitemap.Menu.{Menuable, WithSlash}
import util.Vendor._
import http._
import pkg.UrlRemainder
import code.lib.{MyEasyStatelessDispatch, MyStatelessDispatch}
import Loc._
import widgets.menu.MenuWidget
import widgets.tree.TreeView
import code.snippet.experiments.{FancyMenus, WildProcessing}
import java.io.File
import java.util.UUID

// NOTE: ** is red because Intellij has a bug.

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot extends Loggable {
  def boot() {
    logger.info("Starting bootstrap...")
    // LiftRules singleton provides configuration but is only mutable at boot time.
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // where to search for snippet
    LiftRules.addToPackages("code")
    // will cause search in a subpackage with same path as that of host html file.
    // EG: com/pkg/hostfile.html's snippets could be found in ...snippet.com.pkg.* package
    LiftRules.snippetNamesToSearch.default.set(LiftRules.searchSnippetsWithRequestPath(_: String))

    // Use HTML5 for rendering (instead of the default xhtml)
    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))

    val fancyMenu = {
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
        val item = appendPath(menu / "experiments" / "fancy_menus_by_name", target)
        println("Path labelled '" + label + "' added " + item.path)
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

    // Build SiteMap
    // NOTE:
    // 1) will NOT serve files / folders that start with '.' or '_' or end with '-hidden'
    def siteMap(): SiteMap = {
      SiteMap(
        Menu.i("Home") / "index", // the simple way to declare a menu
        Menu.i("XHTML Experiment") / "xhtml-experiment" >> Hidden, // permitted but not shown in default map
        Menu.i("Experiments") / "experiments" / "index" submenus(
          Menu.i("First Snippet") / "experiments" / "first_snippet", // TODO: Figure out putting link in multiple places in site map
          Menu.i("Embed") / "experiments" / "embed",
          Menu.i("Wildcard Path Starter") / "experiments" / "wild" / "start",
          Menu.param[UrlRemainder]("Wildcard-In-Path",
            "Wildcard-In-Path",
            WildProcessing.parser,
            WildProcessing.encoder) / "experiments" / "wild",
          Menu.i("Recursion") / "experiments" / "recurse",
          Menu.i("Notice") / "experiments" / "notice",
          Menu.i("Redirection") / "experiments" / "redirect",
          Menu.i("Formatting") / "experiments" / "formatting",
          Menu.i("Treeview") / "experiments" / "treeview" / **,
          Menu.i("Ajax") / "experiments" / "ajax",
          Menu.i("Table") / "experiments" / "table",
          Menu.i("Brower Detect") / "experiments" / "browser_detect",
          Menu.i("Chart") / "experiments" / "chart" / **,
          Menu.i("Fancy Menu Hidden") / "experiments" / "fancy_menus" / ** >> Hidden,
          fancyMenu
          ),
        Menu.i("Wildcard Permissions") / "wildcards" submenus (
          //          Is nesting good here?
          Menu.i("Wildly Permitted") / "wild" / ** >> Hidden
          ),
        Menu.i("Css Selector") / "css-select" / **,
        Menu.i("Forms") / "form" / **,
        // using static content technique
        Menu(Loc("widget", new Link(List("widget"), true), "Widgets")),
        // this ends up with 'Widget Anchor Text' in the address bar
        // Menu(Loc("widget", Link(List("widget"), true, "Widget Anchor Text"), "Widgets")),
        Menu.i("Stateless Dispatch") / "stateless-dispatch",
        Menu.i("About") / "meta-content" / "about" >> Hidden >> LocGroup("footer"),
        Menu.i("Contact") / "meta-content" / "contact" >> Hidden >> LocGroup("footer")
      )
    }

    // requests of the form ../fancy_menus_by_name/xxx mapped to ../fancy_menus?fancyParam=xxx
    LiftRules.statefulRewrite.append {
      case RewriteRequest(ParsePath("experiments" :: "fancy_menus_by_name" :: fancyParamList, _, _, _), _, _) =>
        val fancyParam = fancyParamList.mkString(File.separator)
        println("======== Emulating fancyParam=" + fancyParam)
        RewriteResponse(List("experiments", "fancy_menus"), Map(FancyMenus.FancyParam -> fancyParam))
    }

    // Allow duplicate target links in menu
    SiteMap.enforceUniqueLinks = false

    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    //        LiftRules.setSiteMap(siteMap)
    LiftRules.setSiteMapFunc(siteMap _)
    MenuWidget.init()
    TreeView.init()

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Use jQuery 1.4
    LiftRules.jsArtifacts = net.liftweb.http.js.jquery.JQuery14Artifacts

    // stateless dispatch handlers (like for REST)... are these like servlets?
    LiftRules.statelessDispatchTable.append(MyStatelessDispatch.dispatchThis)
    LiftRules.statelessDispatchTable.append(MyEasyStatelessDispatch)
    logger.info("Finished boostrap.")
  }
}
