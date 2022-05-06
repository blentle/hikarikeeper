package org.hikarikeeper.client;

import org.hikarikeeper.core.protocol.Item;
import org.hikarikeeper.core.protocol.ReqHeader;
import org.hikarikeeper.core.protocol.RespHeader;

import java.nio.ByteBuffer;

public final class Packet {

    private ReqHeader reqHeader;

    private RespHeader respHeader;

    private Item request;

    private Item response;

    private ByteBuffer bb;

    private String clientMeta;

    private String serverMeta;

    public boolean readOnly;

    public ReqHeader getReqHeader() {
        return reqHeader;
    }

    public void setReqHeader(ReqHeader reqHeader) {
        this.reqHeader = reqHeader;
    }

    public RespHeader getRespHeader() {
        return respHeader;
    }

    public void setRespHeader(RespHeader respHeader) {
        this.respHeader = respHeader;
    }

    public Item getRequest() {
        return request;
    }

    public void setRequest(Item request) {
        this.request = request;
    }

    public Item getResponse() {
        return response;
    }

    public void setResponse(Item response) {
        this.response = response;
    }

    public ByteBuffer getBb() {
        return bb;
    }

    public void setBb(ByteBuffer bb) {
        this.bb = bb;
    }

    public String getClientMeta() {
        return clientMeta;
    }

    public void setClientMeta(String clientMeta) {
        this.clientMeta = clientMeta;
    }

    public String getServerMeta() {
        return serverMeta;
    }

    public void setServerMeta(String serverMeta) {
        this.serverMeta = serverMeta;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
}
