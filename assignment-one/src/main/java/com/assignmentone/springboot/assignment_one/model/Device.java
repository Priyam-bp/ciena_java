package com.assignmentone.springboot.assignment_one.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("Device")
public class Device {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String deviceType;

    public Device() {
    }

    public Device(Long id, String name, String deviceType) {
        this.id = id;
        this.name = name;
        this.deviceType = deviceType;
    }

    // HAS relationship (DeviceNode)-[HAS]->(ShelfPositionNode)
    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private Set<ShelfPostionVO> shelfPositions = new HashSet<>();

    // Getter functions  
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public Set<ShelfPostionVO> getShelfPositions() {
        return shelfPositions;
    }

    // Setter functions
    public void setName(String name) {
        this.name = name;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public void setShelfPositions(Set<ShelfPostionVO> shelfPositions) {
        this.shelfPositions = shelfPositions;
    }

    @Override
    public String toString() {
        return "Device {id:" + id + ", name: " + name + ", device type:" + deviceType + "}";
    }
}
