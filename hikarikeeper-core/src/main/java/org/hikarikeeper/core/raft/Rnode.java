package org.hikarikeeper.core.raft;

import org.hikarikeeper.core.raft.message.VoteRequestReq;
import org.hikarikeeper.core.raft.message.VoteRequestResp;
import org.hikarikeeper.core.raft.repository.RnodeRepoException;
import org.hikarikeeper.core.raft.scheduler.ElectionTimeoutTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * a real raft node contains an endpoint for client (may be ip:port) and a state for replication
 * @author blentle
 * @since 0.1.0
 *
 */
public class Rnode {

    private static final Logger logger = LoggerFactory.getLogger(Rnode.class);

    private final RnodeEndpoint rnodeEndpoint;

    private final ReplicationState replicationState;

    private final ComponentConnectorContext connectorContext;

    private RnodeRole role;

    private volatile boolean started;

    private VoteRequestRpcContext voteRequestRpcContext;



    public Rnode(RnodeEndpoint rnodeEndpoint, VoteRequestRpcContext voteRequestRpcContext) {
        this(rnodeEndpoint, voteRequestRpcContext, null, null);
    }

    public Rnode(RnodeEndpoint rnodeEndpoint, VoteRequestRpcContext voteRequestRpcContext, ReplicationState replicationState, ComponentConnectorContext connectorContext) {
        this.rnodeEndpoint = rnodeEndpoint;
        this.voteRequestRpcContext = voteRequestRpcContext;
        this.replicationState = replicationState;
        this.connectorContext = connectorContext;
    }

    public synchronized void start() throws RnodeRepoException {
        if (started)
            return;
        //register event
        //init connector
        //change role, initialize current node follower
        RnodeRepository repo = connectorContext.getRepository();
        changeRole(new FollowerNode(repo.getTerm(), null, repo.getVotedFor(), connectorContext.getScheduler().scheduleLogElecTimeoutTask(this::elecTimeout)));
        this.started = true;

    }

    /**
     * this logic shoud be replaced into rpc module
     * @param reqMsg
     */
    public VoteRequestResp handVoteRequestRpc(VoteRequestReq reqMsg) throws RnodeRepoException {
        //request term is less than current term, reject
        long rpcTerm = reqMsg.getTerm();
        long currentTerm = role.getTerm();
        if (rpcTerm < currentTerm) {
            if (logger.isDebugEnabled())
                logger.debug("term from request vote request rpc:{} is less than current term:{}, reject vote", rpcTerm, currentTerm);
            return new VoteRequestResp(currentTerm, false);
        }
        //otherwise vote
        // request term is grater than current term, switch current role to follower
        if (rpcTerm > currentTerm) {
            beFollower(rpcTerm, reqMsg.getCandidateId(), null, true);
            return new VoteRequestResp(rpcTerm, true);
        }
        // rpcTerm eq currentTerm
        return voteRequestRpcContext.process(this, role, reqMsg);
    }

    public void beFollower(long term, RnId votedFor, RnId leaderId, boolean scheduleElecTimeout) throws RnodeRepoException {
        //cancel timeoutOrReplicationTask
        this.role.cancelJob();
        if (Objects.nonNull(leaderId) && (!leaderId.equals(role.getLeaderId(connectorContext.getSelf()))))
            logger.info("current leader:{}, current term:{}", leaderId, term);

        //recreate job
        ElectionTimeoutTask timeoutTask = scheduleElecTimeout ? connectorContext.getScheduler().scheduleLogElecTimeoutTask(this::elecTimeout) : ElectionTimeoutTask.NON;
        changeRole(new FollowerNode(term, leaderId, votedFor, timeoutTask));
    }

    private void changeRole(RnodeRole newRole) throws RnodeRepoException {
        if (logger.isDebugEnabled())
            logger.debug("node:{} role state changed to :{}", connectorContext.getSelf(), newRole);
        RnodeRepository repo = connectorContext.getRepository();
        repo.setTerm(newRole.getTerm());
        if (newRole.getRole().equals(Rrole.follower))
            repo.setVotedFor(((FollowerNode)newRole).getVotedFor());
        this.role = newRole;
    }

    private void elecTimeout() {
        connectorContext.getTaskExecutor().submit(this:: processElecTimeout);
    }

    private void processElecTimeout() {
        // leader timeout do nothing
        if (role.getRole().equals(Rrole.leader)) {
            //do nothing
            if (logger.isDebugEnabled())
                logger.debug("current node: {} is leader, election-timeout,  do nothing", connectorContext.getSelf().getVal());
            return;
        }
        //for follower, begin to elect leader
        //for candidate, a new elections begin
        long newTerm = role.getTerm() + 1;
        role.cancelJob();
        logger.info("begin to elect a leader");
        //change role
        //change self to candidate

        try {
            changeRole(new CandidateNode(newTerm, connectorContext.getScheduler().scheduleLogElecTimeoutTask(this::elecTimeout)));
        } catch (RnodeRepoException e) {
            logger.error("current node: {}, ready to elect a leader, but change role save metadata error", connectorContext.getSelf().getVal(), e);
        }
        //request vote rpc
        VoteRequestReq rpcReq = new VoteRequestReq();
        rpcReq.setTerm(newTerm);
        rpcReq.setCandidateId(connectorContext.getSelf());
        rpcReq.setLastLogIndex(0L);
        rpcReq.setLastLogTerm(0L);
        connectorContext.getRpc().sendRequestVote(rpcReq, connectorContext.getGroup().getMemberListExcludeSelf());
    }
}

