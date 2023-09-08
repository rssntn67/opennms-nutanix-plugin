package org.opennms.nutanix.client.api.model;

public class Entity {
    public enum EntityType {
        Cluster,
        Host,
        VirtualMachine
    }
    public final String state;
    public final String uuid;
    public final String name;
    public final EntityType type;

    public Entity(String state, String uuid, String name, EntityType type) {
        this.state = state;
        this.uuid = uuid;
        this.name = name;
        this.type = type;
    }
}
