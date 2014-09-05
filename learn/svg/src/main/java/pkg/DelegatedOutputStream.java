package pkg;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Routes data to a delegate (I think).
 */
class DelegatedOutputStream extends ServletOutputStream {
    private final OutputStream delegate;

    public DelegatedOutputStream(OutputStream delegate) {
        this.delegate = delegate;
    }

    @Override
    public void write(int b) throws IOException {
        delegate.write(b);
        CapturingResponse.out("wrote " + b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        delegate.write(b, off, len);
        CapturingResponse.out("wrote bytes " + len);
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

    public OutputStream getDelegate() {
        return delegate;
    }
}
