package com.assignmentone.springboot.assignment_one.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignmentone.springboot.assignment_one.model.ShelfPositionVO;
import com.assignmentone.springboot.assignment_one.model.ShelfVO;
import com.assignmentone.springboot.assignment_one.repository.ShelfPositionRepository;
import com.assignmentone.springboot.assignment_one.repository.ShelfRepository;

import jakarta.transaction.Transactional;

@Service
public class ShelfService {
    
    @Autowired
    private ShelfRepository shelfRepository;

    @Autowired 
    private ShelfPositionRepository shelfPositionRepository;

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

    //create shelfpositions on creation of shelf 
    @Transactional
    public ShelfVO createShelf(String name,String shelfType, int shelfPosCount){
        try {
            ShelfVO shelf = new ShelfVO(name,shelfType);
            shelf = shelfRepository.save(shelf);

            Set<ShelfPositionVO> shelfPositions = new HashSet<>();
    
            for(int i = 0;i<shelfPosCount;i++){
                String shelfPosName = shelf.getName() + "-Position" + (i+1);
                ShelfPositionVO sp = new ShelfPositionVO(shelfPosName);

                sp.setShelf(shelf);
                sp.setShelfId(shelf.getId());
                
                sp = shelfPositionRepository.save(sp);
                shelfPositions.add(sp);
            }
            
            shelf.addShelfPositions(shelfPositions);
            
            return shelfRepository.save(shelf);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create device");
        }
    }
}
