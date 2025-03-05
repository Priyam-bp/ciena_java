package com.assignmentone.springboot.assignment_one.model;

public class ShelfPositionDTO {
    private Device device;
    private ShelfPositionVO shelfPosition;
    private ShelfVO shelf;

    public ShelfPositionDTO(ShelfPositionVO shelfPosition, ShelfVO shelf, Device  device){
        this.shelfPosition = shelfPosition;
        this.device = device;
        this.shelf = shelf;
    }

    public Device getDevice() {
        return device;
    }

    public ShelfVO getShelf() {
        return shelf;
    }
    
    public ShelfPositionVO getShelfPosition() {
        return shelfPosition;
    }
}
