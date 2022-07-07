package org.hikarikeeper.core.raft;


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
    private final long electionTimeout;

    public CandidateNode(long term, long electionTimeout) {
        this(term, 1, electionTimeout);
    }

    /**
     * replicate log thread, may return something
     */



    public CandidateNode(long term, int votes, long electionTimeout) {
        super(Rrole.candidate, term);
        this.votes = votes;
        this.electionTimeout = electionTimeout;
    }

    public int getVotes() {
        return votes;
    }

    public long getElectionTimeout() {
        return electionTimeout;
    }

    /**
     * 增加票数,必须得重置选举超时时间
     * @param electionTimeout
     * @return
     */
    public CandidateNode incrVotes(long electionTimeout) {
        return new CandidateNode(this.getTerm(), votes + 1, electionTimeout);

    }
}
