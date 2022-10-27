package org.hikarikeeper.core.raft;


import org.hikarikeeper.core.raft.scheduler.ElectionTimeoutTask;

/**
 * for a candidate node, it has tickets from the followers, and it contains electionTimeout
 * finally it has an external method which increase the votes
 * @author blentle
 * @since 0.1.0
 */
public class CandidateNode extends RnodeRole {

    /**
     * get votes count
     */
    private final int votes;

    /**
     * 选举超时时间.毫秒
     */
    private final ElectionTimeoutTask electionTimeout;

    public CandidateNode(long term, ElectionTimeoutTask electionTimeout) {
        this(term, 1, electionTimeout);
    }

    /**
     * replicate log thread, may return something
     */



    public CandidateNode(long term, int votes, ElectionTimeoutTask electionTimeout) {
        super(Rrole.candidate, term);
        this.votes = votes;
        this.electionTimeout = electionTimeout;
    }

    public int getVotes() {
        return votes;
    }

    public ElectionTimeoutTask getElectionTimeout() {
        return electionTimeout;
    }

    /**
     * 增加票数,必须得重置选举超时时间
     * @param electionTimeout
     * @return
     */
    public CandidateNode incrVotes(ElectionTimeoutTask electionTimeout) {
        return new CandidateNode(this.getTerm(), votes + 1, electionTimeout);

    }

    @Override
    public void cancelJob() {
        electionTimeout.cancel();
    }

    @Override
    public RnId getLeaderId(RnId self) {
        return null;
    }
}
