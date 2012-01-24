package pkg;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Responds with a page showing the parameters or data submitted to the server.
 */
public class EchoServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
                "Transitional//EN\">\n" +
                "<HTML>\n" +
                "<HEAD><TITLE>Echo Servlet</TITLE></HEAD>\n" +
                "<BODY>\n" +
                // A little dodgy... really need to determine deployment context
                "<p>Back to <a href='" + request.getContextPath() + "/..'>main app</p>\n" +
//                "<H1>Hello WWW</H1>\n" +
                "</BODY></HTML>");
    }
}
