package org.hikarikeeper.core.raft;

import org.hikarikeeper.core.raft.message.VoteRequestReq;
import org.hikarikeeper.core.raft.message.VoteRequestResp;
import org.hikarikeeper.core.raft.vote.VotePostProcessor;
import org.hikarikeeper.core.raft.vote.impl.VoteCandidatePostProcessor;
import org.hikarikeeper.core.raft.vote.impl.VoteFollowerPostProcessor;
import org.hikarikeeper.core.raft.vote.impl.VoteLeaderPostProcessor;

import java.util.HashMap;
import java.util.Map;

/**
 * a strategy context that handle logic for different role after request vote
 */
public class VoteRequestRpcContext {

    private static final Map<Rrole, VotePostProcessor> processors = new HashMap();

    static {
        processors.put(Rrole.leader, new VoteLeaderPostProcessor());
        processors.put(Rrole.follower, new VoteFollowerPostProcessor());
        processors.put(Rrole.candidate, new VoteCandidatePostProcessor());
    }

    public VoteRequestResp process(Rnode node, RnodeRole role, VoteRequestReq req) {
        return processors.get(role.getRole()).postProcess(node, role, req);
    }
}
