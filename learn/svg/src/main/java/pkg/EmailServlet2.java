package pkg;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static pkg.StringUtils.escapeHTML;
import static pkg.StringUtils.newlinesToXHTMLBreaks;

/**
 * Trying to use a RequestDispatcher instead.
 */
public class EmailServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher render = req.getRequestDispatcher("/render/index.jsp");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // NOTE: Must 'forward' instead of 'include' for download / header settings to work.
        render.include(req, new CapturingResponse(resp, baos));

        ServletOutputStream stream = resp.getOutputStream();
        stream.println("<html><head><title>Blah</title><body>Size: " + baos.size() + "<br/><pre>");
        stream.println(newlinesToXHTMLBreaks(escapeHTML(baos.toString())));
        stream.print("</pre></body></html>");
    }
}
