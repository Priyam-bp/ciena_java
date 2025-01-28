package com.assignmentone.springboot.assignment_one.model;

public class AddShelfToShelfPosition {

    private long shelfId;
    private long shelfPositionId;

    public AddShelfToShelfPosition(long shelfId, long shelfPositionId){
        this.shelfId = shelfId;
        this.shelfPositionId = shelfPositionId;
    }

    public long getShelfId(){
        return this.shelfId;
    }

    public long getShelfPositionId(){
        return this.shelfPositionId;
    }
}