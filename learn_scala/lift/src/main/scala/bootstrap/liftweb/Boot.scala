package bootstrap.liftweb

import net.liftweb._
import http._
import sitemap.Loc.{LocGroup, Hidden}
import sitemap.{SiteMap, Menu, **}
import pkg.UrlRemainder
import code.snippet.WildProcessing

// NOTE: ** is red because Intellij has a bug.

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
    // NOTE:
    // 1) will NOT serve files / folders that start with '.' or '_' or end with '-hidden'
    def siteMap(): SiteMap = {
      SiteMap(
        Menu.i("Home") / "index", // the simple way to declare a menu
        Menu.i("XHTML Experiment") / "xhtml-experiment" >> Hidden, // permitted but not shown in default map
        Menu.i("Experiments") / "experiments" / "index" submenus (
          Menu.i("First Snippet") / "experiments" / "first_snippet", // TODO: Figure out putting link in multiple places in site map
          Menu.i("Embed") / "experiments" / "embed",
          Menu.i("Wildcard Path Starter") / "experiments" / "wild" / "start",
          Menu.param[UrlRemainder]("Wildcard-In-Path",
            "Wildcard-In-Path",
            WildProcessing.parser,
            WildProcessing.encoder) / "experiments" / "wild",
          Menu.i("Recusion") / "experiments" / "recurse",
          Menu.i("Notice") / "experiments" / "notice"
          ),
        Menu.i("Wildcard Permissions") / "wildcards" submenus (
          Menu.i("Wildly Permitted") / "wild" / ** >> Hidden
          ),
        Menu.i("About") / "meta-content" / "about" >> Hidden >> LocGroup("footer"),
        Menu.i("Contact") / "meta-content" / "contact" >> Hidden >> LocGroup("footer")
      )
    }

    //      // more complex because this menu allows anything in the
    //      // /static path to be visible
    //      Menu(Loc("Static", Link(List("static"), true, "/static/index"),
    //	       "Static Content")))
    //
    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    //        LiftRules.setSiteMap(siteMap)
    LiftRules.setSiteMapFunc(siteMap _)
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
