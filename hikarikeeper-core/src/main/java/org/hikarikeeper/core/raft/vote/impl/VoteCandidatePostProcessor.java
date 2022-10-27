package org.hikarikeeper.core.raft.vote.impl;

import org.hikarikeeper.core.raft.Rnode;
import org.hikarikeeper.core.raft.RnodeRole;
import org.hikarikeeper.core.raft.message.VoteRequestReq;
import org.hikarikeeper.core.raft.message.VoteRequestResp;
import org.hikarikeeper.core.raft.vote.VotePostProcessor;

public class VoteCandidatePostProcessor implements VotePostProcessor {

    @Override
    public VoteRequestResp postProcess(Rnode node, RnodeRole role, VoteRequestReq req) {
        //has voted for self
        return new VoteRequestResp(role.getTerm(),false);
    }
}
