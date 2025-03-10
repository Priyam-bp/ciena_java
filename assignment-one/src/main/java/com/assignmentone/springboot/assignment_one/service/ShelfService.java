package com.assignmentone.springboot.assignment_one.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignmentone.springboot.assignment_one.model.ShelfDTO;
import com.assignmentone.springboot.assignment_one.model.ShelfPositionVO;
import com.assignmentone.springboot.assignment_one.model.ShelfVO;
import com.assignmentone.springboot.assignment_one.repository.ShelfPositionRepository;
import com.assignmentone.springboot.assignment_one.repository.ShelfRepository;


@Service
public class ShelfService {
    
    @Autowired
    private ShelfRepository shelfRepository;

    @Autowired 
    private ShelfPositionRepository shelfPositionRepository;

    @Transactional
    public ShelfVO saveShelf(ShelfVO shelf){
        try {
            return shelfRepository.save(shelf);
        } catch (Exception e) {
            throw new RuntimeException("Unable to save shelves",e);
        }
    }
    
    @Transactional(readOnly = true)
    public ShelfVO getShelf(long id){
        return shelfRepository.findById(id).orElseThrow(()-> new RuntimeException("Shelf not found"));
    }

    @Transactional(readOnly = true)
    public List<ShelfVO> getAllShelves(){
        try {
            return shelfRepository.getAllShelf();
        } catch (Exception e) {
            throw new RuntimeException("Unable to fetch shelves");
        }
    }

    @Transactional
    public ResponseEntity<String> deleteShelfById(long id){
        if(!shelfRepository.findShelfById(id)){
            throw new RuntimeException("Shelf does not exist");
        }
        try {
            shelfRepository.deleteShelfById(id);
            return ResponseEntity.ok("Shelf deleted Succesfully");
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete",e);
        }
    }

    @Transactional(readOnly = true)
    public List<ShelfVO> getAvailableShelves(){
        try {
            return shelfRepository.getAvailableShelves();
        } catch (Exception e) {
           throw new RuntimeException("Unable to fetch shelves");
        }
    }

    @Transactional
    public ShelfVO updateShelf(Long id, ShelfVO shelf){
        if(!shelfRepository.findShelfById(id)){
            throw new RuntimeException("Shelf does not exist");
        }
        try {
            return shelfRepository.editShelf(id, shelf.getName(), shelf.getShelfType());
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

    @Transactional(readOnly = true)
    public List<ShelfVO> getShelfWithShelfPositions(){
        try {
            List<ShelfDTO> res = shelfRepository.getShelfWithShelfPositons();
            Map<Long,ShelfVO> shelfMap = new HashMap<>();
            for(ShelfDTO resItem : res){
                ShelfVO currShelf = shelfMap.get(resItem.getShelf().getId());
                if(currShelf == null){
                    currShelf = resItem.getShelf();
                    shelfMap.put(resItem.getShelf().getId(), currShelf);
                }
                if(resItem.getShelfPositionId() != null){
                    ShelfPositionVO currShelfPosition = new ShelfPositionVO(resItem.getShelfPositionId(),resItem.getShelfPositionName());
                    currShelf.getShelfPositions().add(currShelfPosition);
                }
            }
            return new ArrayList<>(shelfMap.values());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
