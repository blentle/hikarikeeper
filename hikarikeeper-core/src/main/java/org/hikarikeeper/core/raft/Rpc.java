package org.hikarikeeper.core.raft;

import org.hikarikeeper.core.raft.message.VoteRequestReq;

import java.util.Set;

public interface Rpc {

    void init();

    void sendRequestVote(VoteRequestReq rpcReq, Set<RnodeEndpoint> nodeExcludeSelfList);

    void replyRequestVote();

    void sendAppendEntries();

    void replyAppendEntries();

    void close();
}
