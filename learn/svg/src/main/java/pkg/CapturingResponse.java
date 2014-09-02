package pkg;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class CapturingResponse extends HttpServletResponseWrapper {
    private final MyServletOutputStream stream;
    private PrintWriter writer;

    public CapturingResponse(HttpServletResponse response, OutputStream altStream) {
        super(response);
        stream = new MyServletOutputStream(altStream);
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
    public void reset() {
        super.reset();
//        OutputStream delegate = stream.getDelegate();
//        if (delegate instanceof ByteArrayOutputStream) ((ByteArrayOutputStream) delegate).reset();
    }

    private static class MyServletOutputStream extends ServletOutputStream {
        private final OutputStream delegate;

        public MyServletOutputStream(OutputStream delegate) {
            this.delegate = delegate;
        }

        @Override
        public void write(int b) throws IOException {
            delegate.write(b);
            System.out.println("wrote " + b);
        }

        public OutputStream getDelegate() {
            return delegate;
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            delegate.write(b, off, len);
            System.out.println("wrote bytes " + len);
        }



        @Override
        public void flush() throws IOException {
            super.flush();
            delegate.flush();
        }

        @Override
        public void close() throws IOException {
            super.close();
            delegate.close();
        }
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (writer == null) writer = new PrintWriter(new OutputStreamWriter(stream, getCharacterEncoding()));
        return writer;
    }
}
