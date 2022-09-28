package org.hikarikeeper.core.raft;


import org.hikarikeeper.core.raft.scheduler.ElectionTimeoutTask;

/**
 * for a follower node, it needs to know who leader is, who it has voted for and it contains electionTimeout
 * @author blentle
 * @since 0.1.0
 */
public class FollowerNode extends RnodeRole {

    /**
     * who is current leader
     * may be null
     * 当前leader是谁(如果选出来了)
     */
    private final RnId leaderId;

    /**
     * may be null
     * 给谁投过票
     */
    private final RnId votedFor;

    /**
     * 选举超时时间.毫秒
     */
    private final ElectionTimeoutTask electionTimeout;
    /**
     * replicate log thread, may return something
     */

    public FollowerNode(long term, RnId leaderId, RnId votedFor, ElectionTimeoutTask electionTimeout) {
        super(Rrole.follower, term);
        this.leaderId = leaderId;
        this.votedFor = votedFor;
        this.electionTimeout = electionTimeout;
    }

    public RnId getLeaderId() {
        return leaderId;
    }

    public RnId getVotedFor() {
        return votedFor;
    }

    public ElectionTimeoutTask getElectionTimeout() {
        return electionTimeout;
    }
}
