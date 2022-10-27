package org.hikarikeeper.core.raft.vote;

import org.hikarikeeper.core.raft.Rnode;
import org.hikarikeeper.core.raft.RnodeRole;
import org.hikarikeeper.core.raft.message.VoteRequestReq;
import org.hikarikeeper.core.raft.message.VoteRequestResp;

public interface VotePostProcessor {

    VoteRequestResp postProcess(Rnode node, RnodeRole role, VoteRequestReq req);
}
