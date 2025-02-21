package com.assignmentone.springboot.assignment_one.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignmentone.springboot.assignment_one.model.ShelfVO;
import com.assignmentone.springboot.assignment_one.repository.ShelfRepository;

import jakarta.transaction.Transactional;

@Service
public class ShelfService {
    
    @Autowired
    private ShelfRepository shelfRepository;

    public ShelfVO saveShelf(ShelfVO shelf){
        try {
            return shelfRepository.save(shelf);
        } catch (Exception e) {
            throw new RuntimeException("Unable to save shelves",e);
        }
    }

    public ShelfVO getShelf(long id){
        return shelfRepository.findById(id).orElseThrow(()-> new RuntimeException("Shelf not found"));
    }

    public List<ShelfVO> getAllShelves(){
        try {
            return shelfRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to fetch shelves");
        }
    }

    public ResponseEntity<String> deleteShelfById(long id){
        if(!shelfRepository.existsById(id)){
            throw new RuntimeException("Shelf does not exist");
        }
        try {
            shelfRepository.deleteById(id);
            return ResponseEntity.ok("Shelf deleted Succesfully");
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete",e);
        }
    }

    public List<ShelfVO> getAvailableShelves(){
        try {
            return shelfRepository.getAvailableShelves();
        } catch (Exception e) {
           throw new RuntimeException("Unable to fetch shelves");
        }
    }

    @Transactional
    public ShelfVO updateShelf(long id, ShelfVO shelf){
        ShelfVO checkShelf = shelfRepository.findById(id).orElseThrow(()-> new RuntimeException("Shelf Not Found"));
        try {
            checkShelf.setName(shelf.getName());
            return shelfRepository.save(checkShelf);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update shelf");
        }
    }
}
