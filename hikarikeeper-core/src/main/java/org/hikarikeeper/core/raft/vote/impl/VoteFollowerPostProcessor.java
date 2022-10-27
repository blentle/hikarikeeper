package org.hikarikeeper.core.raft.vote.impl;

import org.hikarikeeper.core.raft.FollowerNode;
import org.hikarikeeper.core.raft.RnId;
import org.hikarikeeper.core.raft.Rnode;
import org.hikarikeeper.core.raft.RnodeRole;
import org.hikarikeeper.core.raft.message.VoteRequestReq;
import org.hikarikeeper.core.raft.message.VoteRequestResp;
import org.hikarikeeper.core.raft.repository.RnodeRepoException;
import org.hikarikeeper.core.raft.vote.VotePostProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class VoteFollowerPostProcessor implements VotePostProcessor {

    private final Logger logger = LoggerFactory.getLogger(VoteFollowerPostProcessor.class);
    @Override
    public VoteRequestResp postProcess(Rnode node, RnodeRole role, VoteRequestReq req) {
        FollowerNode follower = (FollowerNode) role;
        RnId votedFor = follower.getVotedFor();
        if (Objects.isNull(votedFor) || votedFor.equals(req.getCandidateId())) {
            try {
                node.beFollower(role.getTerm(), req.getCandidateId(), null, true);
            } catch (RnodeRepoException e) {
                logger.error("request vote post processor persisted error", e);
            }
            return new VoteRequestResp(req.getTerm(), true);
        }
        //has voted for self
        return new VoteRequestResp(role.getTerm(),false);
    }
}
