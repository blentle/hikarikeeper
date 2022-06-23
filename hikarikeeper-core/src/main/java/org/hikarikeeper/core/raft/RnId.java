package org.hikarikeeper.core.raft;

import java.io.Serializable;
import java.util.Objects;

/**
 * a RnId instance represents a unique identity of Raft node
 * @author blentle
 * @since 0.1.o
 *
 */
public class RnId implements Serializable {

    private final String val;

    public RnId(String val) {
        this.val = val;
    }

    public static RnId of(String val) {
        return new RnId(val);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RnId)) return false;
        RnId rnId = (RnId) o;
        return Objects.equals(val, rnId.val);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }

    @Override
    public String toString() {
        return this.val;
    }

    public String getVal() {
        return val;
    }
}
