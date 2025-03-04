package com.assignmentone.springboot.assignment_one.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Node("Shelf")
public class ShelfVO {
    @Id 
    @GeneratedValue
    private Long id;

    private String name;
    private String shelfType;
    private Long shelfPositionId;
    private Boolean active = true;

    public ShelfVO(){}

    public ShelfVO(Long id, String name, String shelfType){
        this.id = id;
        this.name = name;
        this.shelfType = shelfType;
        this.shelfPositionId = null;
    }

    public ShelfVO( String name, String shelfType){
        this.name = name;
        this.shelfType = shelfType;
        this.shelfPositionId = null;
    }

    @Relationship(type = "HAS",direction = Relationship.Direction.INCOMING)
    @JsonIgnoreProperties("shelf")
    private Set<ShelfPositionVO> shelfPositions = new HashSet<>();


    // Getter functions
    public Long getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getShelfType(){
        return this.shelfType;
    }
    public Long getShelfPositionId(){
        return this.shelfPositionId;
    }
    public Boolean getActive(){
        return this.active;
    }
    public Set<ShelfPositionVO> getShelfPositions(){
        return this.shelfPositions;
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
    public void setShelfPositionId(Long shelfPositionId){
        this.shelfPositionId = shelfPositionId;
    }
    public void setActive(Boolean active){
        this.active = active;
    }

    @Override
    public String toString() {
        return "Shelf Details: [id:" + id +" name:" +name+" shelfType:" + shelfType + " shelfPositionId:"+ shelfPositionId +"]";
    }
}
