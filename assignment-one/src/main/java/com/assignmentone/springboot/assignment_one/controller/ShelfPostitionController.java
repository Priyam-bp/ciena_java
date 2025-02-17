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

import com.assignmentone.springboot.assignment_one.model.AddShelfToShelfPosition;
import com.assignmentone.springboot.assignment_one.model.ShelfPositionVO;
import com.assignmentone.springboot.assignment_one.service.ShelfPositionService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/shelfposition")
public class ShelfPostitionController {
    
    @Autowired
    private ShelfPositionService shelfPositionService;

    @PostMapping
    public ShelfPositionVO saveShelfPostion(@RequestBody ShelfPositionVO shelfPosition){
        return shelfPositionService.saveSheldPostion(shelfPosition);
    }   

    @GetMapping("/{id}")
    public ShelfPositionVO getShelfPositionById(@PathVariable long id){
        return shelfPositionService.getShelfPostion(id);
    }

    @GetMapping
    public List<ShelfPositionVO> getAllShelfPositions(){
        return shelfPositionService.getAllshelfPositions();
    }

    @PostMapping("/addShelfToShelfPosition")
    public void addShelfToShelfPosition(@RequestBody AddShelfToShelfPosition data){
        shelfPositionService.addShelfToShelfPosition(data.getShelfId(), data.getShelfPositionId());
    }

    @GetMapping("/getavailableShelfPositions")
    public List<ShelfPositionVO> getAvailableShelfPositions(){
        return shelfPositionService.getAvailableShelfPositions();
    }

    @PutMapping("/{id}")
    public ShelfPositionVO editShelfPosition(@PathVariable long id,@RequestBody ShelfPositionVO shelfPosition){
        return shelfPositionService.editShelfPosition(id, shelfPosition);
    } 

    @DeleteMapping("/{id}")
    public String deleteShelfPosition(@PathVariable long id){
        return shelfPositionService.deleteShelfPosition(id);
    }
}
