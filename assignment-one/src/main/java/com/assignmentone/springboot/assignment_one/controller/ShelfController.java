package com.assignmentone.springboot.assignment_one.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignmentone.springboot.assignment_one.model.ShelfVO;
import com.assignmentone.springboot.assignment_one.service.ShelfService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/shelf")
public class ShelfController {
    
    @Autowired
    private ShelfService shelfService;

    @PostMapping
    public ShelfVO saveShelf(@RequestBody ShelfVO shelf){
        return shelfService.saveShelf(shelf);
    }

    @GetMapping("/{id}")
    public ShelfVO getShelfById(@PathVariable long id){
        return shelfService.getShelf(id);
    }

    @GetMapping
    public List<ShelfVO> getAllShelves(){
        return shelfService.getAllShelves();
    }

    @DeleteMapping("/{id}")
    public String deleteShelfById(@PathVariable long id){
       return shelfService.deleteShelfById(id);
    }

    @GetMapping("/getavailableshelves")
    public List<ShelfVO> getAvailableShelves(){
        return shelfService.getAvailableShelves();
    }

    @PutMapping("/{id}")
    public ShelfVO updateShelf(@PathVariable long id, @RequestBody ShelfVO data){
        return shelfService.updateShelf(id, data);
    }
}
