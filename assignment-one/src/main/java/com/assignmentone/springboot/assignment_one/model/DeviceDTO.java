package com.assignmentone.springboot.assignment_one.model;


public class DeviceDTO {
    private Device device;
    private Long shelfPositionid;
    private String shelfPositionname;

    public DeviceDTO(Device device, Long shelfPositionid,String shelfPositionname){
        this.device = device;
        this.shelfPositionid = shelfPositionid;
        this.shelfPositionname = shelfPositionname;
    }

    public Device getDevice() {
        return device;
    }
    public Long getId() {
        return shelfPositionid;
    }
    public String getName() {
        return shelfPositionname;
    }
    
}