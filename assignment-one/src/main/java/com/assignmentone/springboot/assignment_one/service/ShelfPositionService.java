package com.assignmentone.springboot.assignment_one.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignmentone.springboot.assignment_one.model.ShelfPositionVO;
import com.assignmentone.springboot.assignment_one.model.ShelfVO;
import com.assignmentone.springboot.assignment_one.repository.ShelfPositionRepository;
import com.assignmentone.springboot.assignment_one.repository.ShelfRepository;

@Service
public class ShelfPositionService {
    
    @Autowired
    private ShelfPositionRepository shelfPositionRepository;

    @Autowired
    private ShelfRepository shelfRepository;

    public ShelfPositionVO saveSheldPostion(ShelfPositionVO shelfPosition){
        return shelfPositionRepository.save(shelfPosition);
    }

    public ShelfPositionVO getShelfPostion(long id){
        return shelfPositionRepository.findById(id).orElseThrow(()-> new RuntimeException("Shelf position not found"));
    }

    public List<ShelfPositionVO> getAllshelfPositions(){
        return shelfPositionRepository.findAll();
    }

    public void addShelfToShelfPosition(long shelfId, long shelfPositionId){
        ShelfVO shelf = shelfRepository.findById(shelfId).orElseThrow(()-> new RuntimeException("Shelf not found"));
        ShelfPositionVO shelfPosition = shelfPositionRepository.findById(shelfPositionId).orElseThrow(()-> new RuntimeException("Shelf position not found"));

        if(shelfPosition.getShelf() != null){
            throw new RuntimeException("Shelf position already has a shelf assigned");
        }

        shelf.setShelfPositionId(shelfPositionId);
        shelfRepository.save(shelf);
        shelfPositionRepository.save(shelfPosition);

    }
    
}
