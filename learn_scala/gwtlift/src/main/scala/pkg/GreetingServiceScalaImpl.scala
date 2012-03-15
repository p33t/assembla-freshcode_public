package pkg

import com.google.gwt.user.server.rpc.RemoteServiceServlet
import biz.freshcode.learn.gwtlift.client.GreetingService
import java.lang.String
import biz.freshcode.learn.gwtlift.shared.FieldVerifier
import net.liftweb.http.SHtml
import xml.Text

class GreetingServiceScalaImpl extends RemoteServiceServlet with GreetingService {
  def greetServer(input: String): String = {
    // Verify that the input is valid.
    if (!FieldVerifier.isValidName(input)) {
      throw new IllegalArgumentException("Name must be at least 4 characters long")
    }

    val serverInfo: String = getServletContext.getServerInfo
    val userAgent: String = getThreadLocalRequest.getHeader("User-Agent")

    // Escape data from the client to avoid cross-site script vulnerabilities.
    val altInput = escapeHtml(input)
    val userAgentAlt = escapeHtml(userAgent)

    "Hello, " + altInput + "!<br><br>I am running " + serverInfo +
      ".<br><br>It looks like you are using:<br>" + userAgentAlt
  }

  private def escapeHtml(in: String) = Text(in).toString()
}