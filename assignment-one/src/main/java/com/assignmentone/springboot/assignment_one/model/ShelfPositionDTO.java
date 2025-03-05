package com.assignmentone.springboot.assignment_one.model;

public class ShelfPositionDTO {
    private ShelfPositionVO shelfPosition;

    private String deviceName;
    private String deviceType;
    private Long deviceId;

    private String shelfName;
    private String shelfType;
    private Long shelfId;

    public ShelfPositionDTO(ShelfPositionVO shelfPosition, String deviceName, String deviceType, Long deviceId, String shelfName, String shelfType, Long shelfId){
        this.shelfPosition = shelfPosition;

        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;

        this.shelfId = shelfId;
        this.shelfName = shelfName;
        this.shelfType = shelfType;
    }

    public Long getDeviceId() {
        return deviceId;
    }
    public String getDeviceName() {
        return deviceName;
    }
    public String getDeviceType() {
        return deviceType;
    }


    public Long getShelfId() {
        return shelfId;
    }
    public String getShelfName() {
        return shelfName;
    }
    public String getShelfType() {
        return shelfType;
    }
    
    public ShelfPositionVO getShelfPosition() {
        return shelfPosition;
    }
}
