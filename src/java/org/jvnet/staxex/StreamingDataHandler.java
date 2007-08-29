package org.jvnet.staxex;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import java.io.InputStream;
import java.io.IOException;
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
     * Gives data only one time. The data can be accessed in a
     * streaming fashion and the implementations may implement
     * this without buffering.
     *
     * @return content of this object
     * @throws java.io.IOException if any i/o error
     */
    public abstract InputStream readOnce() throws IOException;

}

