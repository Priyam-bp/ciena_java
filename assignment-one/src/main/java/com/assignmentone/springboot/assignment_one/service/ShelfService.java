package com.assignmentone.springboot.assignment_one.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignmentone.springboot.assignment_one.model.ShelfVO;
import com.assignmentone.springboot.assignment_one.repository.ShelfRepository;

@Service
public class ShelfService {
    
    @Autowired
    private ShelfRepository shelfRepository;

    public ShelfVO saveShelf(ShelfVO shelf){
        return shelfRepository.save(shelf);
    }

    public ShelfVO getShelf(long id){
        return shelfRepository.findById(id).orElse(null);
    }

    public List<ShelfVO> getAllShelves(){
        return shelfRepository.findAll();
    }

    public void deleteShelfById(long id){
        shelfRepository.deleteById(id);
    }
}
