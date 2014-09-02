package pkg;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import static pkg.StringUtils.escapeHTML;
import static pkg.StringUtils.newlinesToXHTMLBreaks;

public class EmailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer sb = req.getRequestURL();
        sb.delete(sb.lastIndexOf("/") + 1, sb.length());
        sb.append("render?x=5&y=5&count=2&command=Show");

        // from http://stackoverflow.com/questions/15204591/can-plain-java-object-utilize-capture-include-jsp-output
        URL url = new URL(sb.toString());
        URLConnection openConnection = url.openConnection();
        openConnection.setReadTimeout(500);
        InputStream inputStream = openConnection.getInputStream();
        int read;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        do {
            byte[] bytes = new byte[1024];
            read = inputStream.read(bytes);
            baos.write(bytes);
        } while (read > 0);
//        baos.toString();
        ServletOutputStream stream = resp.getOutputStream();
        stream.println("<html><head><title>Blah</title><body><pre>");
        stream.println(newlinesToXHTMLBreaks(escapeHTML(baos.toString())));
        stream.print("</pre></body></html>");
    }
}
