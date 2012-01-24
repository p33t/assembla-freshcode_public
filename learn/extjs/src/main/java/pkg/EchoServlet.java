package pkg;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Responds with a page showing the parameters or data submitted to the server.
 */
public class EchoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        respond(req, resp);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        respond(request, response);
    }

    private void respond(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
                "Transitional//EN\">\n" +
                "<HTML>\n" +
                "<HEAD><TITLE>Echo Servlet</TITLE></HEAD>\n" +
                "<BODY>\n" +
                // A little dodgy... really need to determine deployment context
                "<p>Back to <a href='" + request.getContextPath() + "/..'>main app</a></p>\n" +
                "<p>" + request.getMethod() + "</p>\n" +
                "<pre>" + display(request) + "</pre>\n" +
                "</BODY></HTML>");
    }

    private String display(HttpServletRequest req) {

        String s = "Parameters:";
        for (Enumeration names = req.getParameterNames(); names.hasMoreElements(); ) {
            String name = (String) names.nextElement();
            String[] vals = req.getParameterValues(name);
            s += "\n" + name + ": ";
            String valString = "";
            for (String val: vals) {
                if (!valString.equals("")) valString += ", ";
                valString +=  val;
            }
            s += valString;
        }
        return s;
    }
}
