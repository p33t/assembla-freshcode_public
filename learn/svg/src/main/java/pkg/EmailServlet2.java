package pkg;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static pkg.StringUtils.escapeHTML;
import static pkg.StringUtils.newlinesToXHTMLBreaks;

/**
 * Trying to use a RequestDispatcher instead.
 */
public class EmailServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher render = req.getRequestDispatcher("/render/index.jsp");
        HttpServletResponseWrapper altResp = new HttpServletResponseWrapper(resp){
            private StringWriter writer = new StringWriter();

            @Override
            public PrintWriter getWriter() throws IOException {
                return new PrintWriter(writer);
            }

            @Override
            public String toString() {
                return writer.toString();

            }
        };
        // NOTE: Must 'forward' instead of 'include' for download / header settings to work.
        render.include(req, altResp);

        ServletUtils.noCache(resp);
        ServletOutputStream stream = resp.getOutputStream();
        String str = altResp.toString();
        stream.println("<html><head><title>Blah</title><body>Size: " + str.length() + "<br/><pre>");
        stream.println(newlinesToXHTMLBreaks(escapeHTML(str)));
        stream.print("</pre></body></html>");
    }
}
