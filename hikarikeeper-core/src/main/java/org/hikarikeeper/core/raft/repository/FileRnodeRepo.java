package org.hikarikeeper.core.raft.repository;

import org.hikarikeeper.core.raft.RnId;
import org.hikarikeeper.core.raft.RnodeRepository;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

public class FileRnodeRepo implements RnodeRepository {


    private static final String DEFAULT_FILE_NAME = "node-bin";

    private static final int TERM_OFFSET = 0;

    private static final int VOTED_FOR_OFFSET = 8;

    private final SeekableByteChannel channel;

    private long term;

    private RnId votedFor;

    public FileRnodeRepo(File file) throws IOException {
        //check
        if (!file.exists())
            file.createNewFile();
        //noinspection resource[todo: try with resource]
        this.channel = new RandomAccessFile(file, "rw").getChannel();
        initOrLoad();
    }

    private void initOrLoad() throws IOException {
        //todo:read votedFor, Considering make nodeId int or long
        if (channel.position() <= 0) {
            //init term-size + voterForId-size(?)
            long size = VOTED_FOR_OFFSET + 0;
            channel.truncate(size);
            channel.position(0L);
            channel.write(ByteBuffer.allocate(VOTED_FOR_OFFSET));
            //votedFor-size(?)
            channel.write(ByteBuffer.allocate(0));
        } else {
            //load
            ByteBuffer bb = ByteBuffer.allocate(VOTED_FOR_OFFSET);
            channel.read(bb);
            this.term = bb.getLong();
        }
    }


    @Override
    public long getTerm() {
        return this.term;
    }

    @Override
    public void setTerm(long term) throws RnodeRepoException {
        try {
            channel.position(TERM_OFFSET);
            channel.write(ByteBuffer.allocate(VOTED_FOR_OFFSET).putLong(term));
        } catch (IOException e) {
            throw new RnodeRepoException("set term error");
        }
        this.term = term;
    }

    @Override
    public RnId getVotedFor() {
        return this.votedFor;
    }

    @Override
    public void setVotedFor(RnId candidate) throws RnodeRepoException {
        try {
            channel.position(VOTED_FOR_OFFSET);
            //todo:set votedFor size
        } catch (IOException e) {
            throw new RnodeRepoException("set votedFor error");
        }
        this.votedFor = votedFor;
    }

    @Override
    public void close() throws RnodeRepoException {
        try {
            this.channel.close();
        } catch (IOException e) {
            throw new RnodeRepoException("close node file channel error");
        }
    }
}
