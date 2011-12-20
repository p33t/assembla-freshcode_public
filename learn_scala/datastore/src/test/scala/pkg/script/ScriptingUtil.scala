package pkg.script

import javax.script.ScriptEngineManager

object ScriptingUtil {
  lazy val JsFactory = {
    import scala.collection.JavaConversions._
    val factories = new ScriptEngineManager().getEngineFactories
    factories.find(_.getMimeTypes.contains("text/javascript")).get
  }
}