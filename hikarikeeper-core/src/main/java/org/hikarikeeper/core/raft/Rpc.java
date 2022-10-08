package org.hikarikeeper.core.raft;

import org.hikarikeeper.core.raft.message.VoteRequestReq;

public interface Rpc {

    void init();

    void sendRequestVote(VoteRequestReq rpcReq, RnodeGroup group);

    void replyRequestVote();

    void sendAppendEntries();

    void replyAppendEntries();

    void close();
}
