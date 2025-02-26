package com.assignmentone.springboot.assignment_one.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignmentone.springboot.assignment_one.model.ShelfPositionVO;
import com.assignmentone.springboot.assignment_one.model.ShelfVO;
import com.assignmentone.springboot.assignment_one.repository.ShelfPositionRepository;
import com.assignmentone.springboot.assignment_one.repository.ShelfRepository;

import jakarta.transaction.Transactional;

@Service
public class ShelfPositionService {
    
    @Autowired
    private ShelfPositionRepository shelfPositionRepository;

    @Autowired
    private ShelfRepository shelfRepository;

    public ShelfPositionVO saveSheldPostion(ShelfPositionVO shelfPosition){
        try {
            return shelfPositionRepository.save(shelfPosition);
        } catch (Exception e) {
            throw new RuntimeException("Unable to save Shelf Positions",e);
        }
    }

    public ShelfPositionVO getShelfPostion(long id){
        return shelfPositionRepository.findById(id).orElseThrow(()-> new RuntimeException("Shelf position not found"));
    }

    public List<ShelfPositionVO> getAllshelfPositions(){
        try {
            return shelfPositionRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to fetch all Shelf Positions",e);
        }
    }

    public ResponseEntity<String> addShelfToShelfPosition(long shelfId, long shelfPositionId){
        try {
            ShelfVO shelf = shelfRepository.findById(shelfId).orElseThrow(()-> new RuntimeException("Shelf not found"));
            ShelfPositionVO shelfPosition = shelfPositionRepository.findById(shelfPositionId).orElseThrow(()-> new RuntimeException("Shelf position not found"));

            if(shelfPosition.getShelf() != null){
                throw new RuntimeException("Shelf position already has a shelf assigned");
            }

            shelfPosition.setShelf(shelf);
            shelfPositionRepository.save(shelfPosition);

            return ResponseEntity.ok("Relationship established succesfully");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    public List<ShelfPositionVO> getAvailableShelfPositions(){
        try {
            return shelfPositionRepository.getAvailableShelfPositions();
        } catch (Exception e) {
            throw new RuntimeException("Unable to fetch available Shelf",e);
        }
    }

    @Transactional
    public ShelfPositionVO editShelfPosition(long id,ShelfPositionVO shelfPosition){
        ShelfPositionVO checkShelfPosition = shelfPositionRepository.findById(id).orElseThrow(()->new RuntimeException("Shelf Position not Found"));
        try {
            checkShelfPosition.setName(shelfPosition.getName());
            return shelfPositionRepository.save(checkShelfPosition);
        } catch (Exception e) {
            throw new RuntimeException("Unable to edit",e);
        }
    }

    public String deleteShelfPosition(long id){
        if(!shelfPositionRepository.existsById(id)){
            throw new RuntimeException("Shelf Position not found");
        }
        try {
            shelfPositionRepository.deleteById(id);
            return "Shelf Position deleted of id:" + id;
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete",e);
        }
    }
}
