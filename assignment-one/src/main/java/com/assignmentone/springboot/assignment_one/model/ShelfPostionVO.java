package com.assignmentone.springboot.assignment_one.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("Shelf Position")
public class ShelfPostionVO {
    @Id
    private long id;
    private String name;
    private long deviceId;

    public ShelfPostionVO(long id,String name, long deviceId){
        this.id = id;
        this.name = name;
        this.deviceId = deviceId;
    }

    //Relationship (shelfPostitionNode)-[HAS]->(shelf)
    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private Set<ShelfVO> shelf = new HashSet<>();

    public long getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public long getDeviceId(){
        return this.deviceId;
    }

    public Set<ShelfVO> getShelf(){
        return shelf;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setDeviceId(long deviceId){
        this.deviceId = deviceId;
    }

    public void setShelf(Set<ShelfVO> shelf){
        this.shelf = shelf;
    }
}

