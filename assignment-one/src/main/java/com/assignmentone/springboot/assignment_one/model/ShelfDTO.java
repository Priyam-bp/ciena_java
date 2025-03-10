package com.assignmentone.springboot.assignment_one.model;

public class ShelfDTO {
    private ShelfVO shelf;
    private Long shelfPositionId;
    private String shelfPositionName;

    public ShelfDTO(ShelfVO shelf,Long shelfPositionId,String shelfPositionName){
        this.shelf = shelf;
        this.shelfPositionId = shelfPositionId;
        this.shelfPositionName = shelfPositionName;
    }

    public ShelfVO getShelf() {
        return shelf;
    }
    public Long getShelfPositionId() {
        return shelfPositionId;
    }
    public String getShelfPositionName() {
        return shelfPositionName;
    }
}
