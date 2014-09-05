package pkg;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class CapturingResponse extends HttpServletResponseWrapper {
    private final DelegatedOutputStream stream;
    private final OutputStream altStream;
    private PrintWriter writer;

    public CapturingResponse(HttpServletResponse response, OutputStream altStream) {
        super(response);
        stream = new DelegatedOutputStream(altStream);
        this.altStream = altStream;
    }

    public static void out(String msg) {
        System.out.println(msg);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return stream;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (writer != null) writer.flush();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        out("getWriter() called");
        if (writer == null) writer = new PrintWriter(new OutputStreamWriter(stream, getCharacterEncoding()));
        return writer;
    }
}
