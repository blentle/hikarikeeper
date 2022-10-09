package org.hikarikeeper.core.raft;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * this class maintains a map(RnId -> RnodeGroupMember) for all raft node members
 * @author blentle
 * @since 0.1.0
 *
 */
public class RnodeGroup {

    private final RnId self;

    private Map<RnId, RnodeGroupMember> memberMap;


    public RnodeGroup(RnId self, Map<RnId, RnodeGroupMember> memberMap) {
        this.self = self;
        this.memberMap = memberMap;
    }

    public Set<RnodeEndpoint> getMemberListExcludeSelf() {
        Set<RnodeEndpoint> set = new HashSet<>();
        memberMap.forEach((rid, mem) -> {
            RnodeEndpoint endpoint = mem.getEndpoint();
            if (!endpoint.getNodeId().equals(self))
                set.add(endpoint);
        });
        return set;
    }
}
