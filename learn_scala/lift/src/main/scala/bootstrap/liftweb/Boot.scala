package bootstrap.liftweb

import net.liftweb._
import http._
import sitemap.{SiteMap, Menu}

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot {
    // LiftRules singleton provides configuration but is only mutable at boot time.
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // where to search for snippet
    LiftRules.addToPackages("code")

    // Use HTML5 for rendering (instead of the default xhtml)
    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))

    // Build SiteMap
    //    def sitemap(): SiteMap = SiteMap(Menu.i("Home") / "index")
    def siteMap(): SiteMap = SiteMap(
      Menu.i("Home") / "index" // the simple way to declare a menu
      // TODO: Finish sub menus
      //      , Menu.i("Experiments") / "experiments" submenus (
//        Menu.i("My first snippet") / "first_snippet"
//        )
    )

    //      // more complex because this menu allows anything in the
    //      // /static path to be visible
    //      Menu(Loc("Static", Link(List("static"), true, "/static/index"),
    //	       "Static Content")))
    //
    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    //        LiftRules.setSiteMap(siteMap)
    LiftRules.setSiteMapFunc(() => siteMap()) // TODO: Try siteMap _
    //
    //    //Show the spinny image when an Ajax call starts
    //    LiftRules.ajaxStart =
    //      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)
    //
    //    // Make the spinny image go away when it ends
    //    LiftRules.ajaxEnd =
    //      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)
    //
    //    // Use jQuery 1.4
    //    LiftRules.jsArtifacts = net.liftweb.http.js.jquery.JQuery14Artifacts
    //
    // Force the request to be UTF-8
  }
}
