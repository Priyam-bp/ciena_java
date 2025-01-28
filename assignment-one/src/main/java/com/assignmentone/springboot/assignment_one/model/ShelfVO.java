package com.assignmentone.springboot.assignment_one.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Shelf")
public class ShelfVO {
    @Id @GeneratedValue
    private long id;
    private String name;
    private String shelfType;
    private long shelfPositionId;

    public ShelfVO(long id, String name, String shelfType,long shelfPositionId){
        this.name = name;
        this.shelfType = shelfType;
        this.shelfPositionId = shelfPositionId;
    }

    // Getter functions
    public long getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getShelfType(){
        return this.shelfType;
    }
    public long getShelfPositionId(){
        return this.shelfPositionId;
    }

    //setter functions
    public void setId(long id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setShelfType(String shelfType){
        this.shelfType = shelfType;
    }
    public void setShelfPositionId(long shelfPositionId){
        this.shelfPositionId = shelfPositionId;
    }

    @Override
    public String toString() {
        return "Shelf Details: [id:" + id +" name:" +name+" shelfType:" + shelfType + " shelfPositionId:"+ shelfPositionId +"]";
    }
}
