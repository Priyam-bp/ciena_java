package com.assignmentone.springboot.assignment_one.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Node("Device")
public class Device {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String deviceType;
    private Boolean active = true; // true: active , false: deleted

    public Device() {
    }

    public Device(Long deviceId, String name, String deviceType) {
        this.name = name;
        this.deviceType = deviceType;
    }

    // HAS relationship (DeviceNode)-[HAS]->(ShelfPositionNode)
    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    @JsonIgnoreProperties("device")
    private Set<ShelfPositionVO> shelfPositions = new HashSet<>();

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

    public Set<ShelfPositionVO> getShelfPositions() {
        return shelfPositions;
    }

    public boolean getActive(){
        return active;
    }

    // Setter functions
    public void setName(String name) {
        this.name = name;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public void setShelfPositions(Set<ShelfPositionVO> shelfPositions) {
        this.shelfPositions = shelfPositions;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    @Override
    public String toString() {
        return "Device {id:" + id + ", name: " + name + ", device type:" + deviceType + "}";
    }
}
