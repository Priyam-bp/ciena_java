package com.assignmentone.springboot.assignment_one.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("ShelfPosition")
public class ShelfPostionVO {
    @Id 
    @GeneratedValue
    private Long id;
    private String name;
    

    public ShelfPostionVO() {}

    public ShelfPostionVO(String name){
        this.name = name;
    }

    //Relationship (shelfPostitionNode)-[HAS]->(shelf)
    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private Set<ShelfVO> shelf = new HashSet<>();

    //Relationship (Device)-[HAS]->(shelfPosition)
    @Relationship(type = "HAS", direction = Relationship.Direction.INCOMING)
    private Device device;

    public Long getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public Device getDevices(){
        return this.device;
    }

    public Set<ShelfVO> getShelf(){
        return shelf;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setDevices(Device device){
        this.device = device;
    }

    public void setShelf(Set<ShelfVO> shelf){
        this.shelf = shelf;
    }
}

