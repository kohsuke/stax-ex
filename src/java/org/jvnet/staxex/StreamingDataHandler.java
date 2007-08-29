package org.jvnet.staxex;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import java.io.InputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.net.URL;

/**
 * {@link DataHandler} extended to offer better buffer management
 * in a streaming environment.
 *
 * <p>
 * {@link DataHandler} is used commonly as a data format across
 * multiple systems (such as JAXB/WS.) Unfortunately, {@link DataHandler}
 * has the semantics of "read as many times as you want", so this makes
 * it difficult for involving parties to handle a BLOB in a streaming fashion.
 *
 * <p>
 * {@link StreamingDataHandler} solves this problem by offering methods
 * that enable faster bulk "consume once" read operation.
 *
 * @author Jitendra Kotamraju
 */
public abstract class StreamingDataHandler extends DataHandler {

    public StreamingDataHandler(Object o, String s) {
        super(o, s);
    }

    public StreamingDataHandler(URL url) {
        super(url);
    }

    public StreamingDataHandler(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * Works like {@link #getInputStream()} except that this method
     * can be invoked only once.
     *
     * <p>
     * This is used as a signal from the caller that there will
     * be no further {@link #getInputStream()} invocation nor
     * {@link #readOnce()} invocation on this object (which would
     * result in {@link IOException}.)
     *
     * <p>
     * When {@link DataHandler} is backed by a streaming BLOB
     * (such as an attachment in a web service read from the network),
     * this allows the callee to avoid unnecessary buffering.
     *
     * <p>
     * Note that it is legal to call {@link #getInputStream()}
     * multiple times and then call {@link #readOnce()} afterward.
     * Streams created such a way can be read in any order &mdash;
     * there's no requirement that streams created earlier must be read
     * first.
     *
     * @return
     *      always non-null. Represents the content of this BLOB.
     *      The returned stream is generally not buffered, so for
     *      better performance read in a big batch or wrap this into
     *      {@link BufferedInputStream}.
     * @throws IOException
     *      if any i/o error
     */
    public abstract InputStream readOnce() throws IOException;

}

