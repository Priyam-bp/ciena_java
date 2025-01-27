package com.assignmentone.springboot.assignment_one.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;


@Node
public class Device {
    @Id
    private long id;
    private String name;
    private String deviceType;

    public Device(long id, String name, String deviceType){
        this.id = id;
        this.name = name;
        this.deviceType = deviceType;
    }   


    //getter functions  
    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getDeviceType(){
        return deviceType;
    }

    @Override
    public String toString() {
        return "Device {id:" + id +", name: " + name +", device type:" +deviceType + "}";
    }
}
