package com.assignmentone.springboot.assignment_one.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignmentone.springboot.assignment_one.model.ShelfPostionVO;
import com.assignmentone.springboot.assignment_one.repository.ShelfPositionRepository;

@Service
public class ShelfPositionService {
    
    @Autowired
    private ShelfPositionRepository shelfPositionRepository;

    public ShelfPostionVO saveSheldPostion(ShelfPostionVO shelfPosition){
        return shelfPositionRepository.save(shelfPosition);
    }

    public ShelfPostionVO getShelfPostion(long id){
        return shelfPositionRepository.findById(id).orElse(null);
    }

    
}
