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
        return shelfRepository.findById(id).orElseThrow(()-> new RuntimeException("Shelf not found"));
    }

    public List<ShelfVO> getAllShelves(){
        return shelfRepository.findAll();
    }

    public String deleteShelfById(long id){
        shelfRepository.deleteById(id);
        return "Shelf deleted of id:"+id;
    }

    public List<ShelfVO> getAvailableShelves(){
        return shelfRepository.getAvailableShelves();
    }

    public ShelfVO updateShelf(long id, ShelfVO shelf){
        ShelfVO checkShelf = shelfRepository.findById(id).orElseThrow(()-> new RuntimeException("Shelf Not Found"));
        checkShelf.setName(shelf.getName());
        return shelfRepository.save(checkShelf);
    }
}
