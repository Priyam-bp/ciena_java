package com.assignmentone.springboot.assignment_one.model;

public class AddShelfPositionRequest {
    private long deviceId;
    private long shelfPositionId;

    public AddShelfPositionRequest(long deviceId, long shelfPositionId){
        this.deviceId = deviceId;
        this.shelfPositionId = shelfPositionId;
    }

    public long getDeviceId(){
        return this.deviceId;
    }
    public long getshelfPositionId(){
        return this.shelfPositionId;
    }


    public void setDeviceId(long deviceId){
        this.deviceId = deviceId;
    }

    public void setShelfPositionId(long shelfPositionId){
        this.shelfPositionId = shelfPositionId;
    }
}
