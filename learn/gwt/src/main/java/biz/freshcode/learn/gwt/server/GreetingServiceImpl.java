package biz.freshcode.learn.gwt.server;

import biz.freshcode.learn.gwt.mod1.client.GreetingService;
import biz.freshcode.learn.gwt.mod1.client.inject.SessionInfo;
import biz.freshcode.learn.gwt.mod1.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import javax.servlet.http.Cookie;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings({"serial", "GwtServiceNotRegistered"})
public class GreetingServiceImpl extends RemoteServiceServlet implements
    GreetingService {

  public String greetServer(String input) throws IllegalArgumentException {
    // Verify that the input is valid.
    if (!FieldVerifier.isValidName(input)) {
      // If the input is not valid, throw an IllegalArgumentException back to
      // the client.
      throw new IllegalArgumentException(
          "Name must be at least 4 characters long");
    }

    String serverInfo = getServletContext().getServerInfo();
    String userAgent = getThreadLocalRequest().getHeader("User-Agent");

    // Escape data from the client to avoid cross-site script vulnerabilities.
    input = escapeHtml(input);
    userAgent = escapeHtml(userAgent);

    return "Hello, " + input + "!<br><br>I am running " + serverInfo
        + ".<br><br>It looks like you are using:<br>" + userAgent;
  }

    @Override
    public String readCookie(String cookieName) {
        for (Cookie c: getThreadLocalRequest().getCookies()) {
            if (c.getName().equals(cookieName)) return c.getValue();
        }
        return null;
    }

    @Override
    public SessionInfo.Bean loadSessionInfo() {
        SessionInfo.Bean b = new SessionInfo.Bean();
        b.setUserName(getThreadLocalRequest().getRemoteUser());
        return b;
    }

    /**
   * Escape an html string. Escaping data received from the client helps to
   * prevent cross-site script vulnerabilities.
   *
   * @param html the html string to escape
   * @return the escaped string
   */
  private String escapeHtml(String html) {
    if (html == null) {
      return null;
    }
    return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
        ">", "&gt;");
  }
}
