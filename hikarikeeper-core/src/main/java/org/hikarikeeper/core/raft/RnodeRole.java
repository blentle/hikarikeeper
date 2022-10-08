package org.hikarikeeper.core.raft;

/**
 * abstract raft role
 * @author blentle
 * @since 0.1.0
 */
public abstract class RnodeRole {

    private Rrole role;

    private long term;

    public RnodeRole(Rrole role, long term) {
        this.role = role;
        this.term = term;
    }

    public Rrole getRole() {
        return role;
    }

    public long getTerm() {
        return term;
    }

    public abstract void cancelJob();

}
