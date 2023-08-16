package org.opennms.nutanix.client.api.model;

public class Entity {
    public final String state;
    public final String uuid;

    //From Cluster.status
    public final String name;

    public Entity(String state, String uuid, String name) {
        this.state = state;
        this.uuid = uuid;
        this.name = name;
    }
}
