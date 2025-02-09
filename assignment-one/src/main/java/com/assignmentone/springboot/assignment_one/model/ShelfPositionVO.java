package com.assignmentone.springboot.assignment_one.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Node("ShelfPosition")
public class ShelfPositionVO {
    @Id 
    @GeneratedValue
    private Long id;
    private String name;

    public ShelfPositionVO() {}

    public ShelfPositionVO(String name) {
        this.name = name;
    }

    // One-to-One Relationship (ShelfPositionVO)-[HAS]->(ShelfVO)
    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private ShelfVO shelf;

    // Relationship (Device)-[HAS]->(ShelfPositionVO)
    @Relationship(type = "HAS", direction = Relationship.Direction.INCOMING)
    @JsonIgnoreProperties("shelfPosition")
    private Device device;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Device getDevice() {
        return this.device;
    }

    public ShelfVO getShelf() {
        return shelf;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public void setShelf(ShelfVO shelf) {
        this.shelf = shelf;
    }
}
