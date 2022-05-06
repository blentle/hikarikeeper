package org.hikarikeeper.core.protocol;

import java.io.IOException;

/**
 * a packet info
 */
public interface Item {

    void seri() throws IOException;

    void deseri() throws IOException;
}
