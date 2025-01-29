package com.assignmentone.springboot.assignment_one.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignmentone.springboot.assignment_one.model.ShelfPostionVO;
import com.assignmentone.springboot.assignment_one.model.ShelfVO;
import com.assignmentone.springboot.assignment_one.repository.ShelfPositionRepository;
import com.assignmentone.springboot.assignment_one.repository.ShelfRepository;

@Service
public class ShelfPositionService {
    
    @Autowired
    private ShelfPositionRepository shelfPositionRepository;

    @Autowired
    private ShelfRepository shelfRepository;

    public ShelfPostionVO saveSheldPostion(ShelfPostionVO shelfPosition){
        return shelfPositionRepository.save(shelfPosition);
    }

    public ShelfPostionVO getShelfPostion(long id){
        return shelfPositionRepository.findById(id).orElse(null);
    }

    public List<ShelfPostionVO> getAllshelfPositions(){
        return shelfPositionRepository.findAll();
    }

    public void addShelfToShelfPosition(long shelfId, long shelfPositionId){
        Optional<ShelfVO> shelfOptional = shelfRepository.findById(shelfId);
        Optional<ShelfPostionVO> shelfPositionOptional = shelfPositionRepository.findById(shelfPositionId);
    
        if(shelfOptional.isPresent() && shelfPositionOptional.isPresent()){
            ShelfVO shelf = shelfOptional.get();
            ShelfPostionVO shelfPosition = shelfPositionOptional.get();

            shelf.setShelfPositionId(shelfPositionId);
    
            Set<ShelfVO> shelfs = shelfPosition.getShelf();
            if(shelfs == null){
                shelfs = new HashSet<>();
            }
    
            if (!shelfs.contains(shelf)) {
                shelfs.add(shelf);
                shelfPosition.setShelf(shelfs);
                
                shelfPositionRepository.save(shelfPosition);
            }
        }
    }
    
}
