package org.jvnet.staxex;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;

/**
 * A {@link DataHandler} that provides access to the streaming data.
 * The data can be read only once.
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

