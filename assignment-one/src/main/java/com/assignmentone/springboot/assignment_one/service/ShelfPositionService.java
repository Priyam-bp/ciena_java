package com.assignmentone.springboot.assignment_one.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignmentone.springboot.assignment_one.model.Device;
import com.assignmentone.springboot.assignment_one.model.ShelfPositionDTO;
import com.assignmentone.springboot.assignment_one.model.ShelfPositionVO;
import com.assignmentone.springboot.assignment_one.model.ShelfVO;
import com.assignmentone.springboot.assignment_one.repository.ShelfPositionRepository;
import com.assignmentone.springboot.assignment_one.repository.ShelfRepository;



@Service
public class ShelfPositionService {
    
    @Autowired
    private ShelfPositionRepository shelfPositionRepository;

    @Autowired
    private ShelfRepository shelfRepository;

    @Transactional
    public ShelfPositionVO saveSheldPostion(ShelfPositionVO shelfPosition){
        try {
            return shelfPositionRepository.save(shelfPosition);
        } catch (Exception e) {
            throw new RuntimeException("Unable to save Shelf Positions",e);
        }
    }

    @Transactional(readOnly = true)
    public ShelfPositionVO getShelfPostion(long id){
        return shelfPositionRepository.getShelfPosById(id).orElseThrow(()-> new RuntimeException("Shelf position not found"));
    }

    @Transactional(readOnly = true)
    public List<ShelfPositionVO> getAllshelfPositions(){
        try {
            List<ShelfPositionDTO> res = shelfPositionRepository.getAllShelfPos();
            List<ShelfPositionVO> shelfPositions = new ArrayList<>();

            if(res != null && !res.isEmpty()){
                for(ShelfPositionDTO resItem : res){
                    ShelfPositionVO shelfPosition = resItem.getShelfPosition();

                    Long deviceId = resItem.getDeviceId();
                    String deviceName = resItem.getDeviceName();
                    String deviceType = resItem.getDeviceType();

                    Long shelfId = resItem.getShelfId();
                    String shelfName = resItem.getShelfName();
                    String shelfType = resItem.getShelfType();

                    if(deviceId != null){
                        Device device = new Device(deviceId,deviceName,deviceType);
                        shelfPosition.setDevice(device);
                    }

                    if(shelfId != null){
                        ShelfVO shelf = new ShelfVO(shelfId,shelfName,shelfType);
                        shelfPosition.setShelf(shelf);
                    }


                    shelfPositions.add(shelfPosition);
                }
            }
            return shelfPositions;

        } catch (Exception e) {
            throw new RuntimeException("Unable to fetch all Shelf Positions",e);
        }
    }

    @Transactional
    public ResponseEntity<String> addShelfToShelfPosition(long shelfId, long shelfPositionId){
        try {
            ShelfVO shelf = shelfRepository.findById(shelfId).orElseThrow(()-> new RuntimeException("Shelf not found"));
            ShelfPositionVO shelfPosition = shelfPositionRepository.getShelfPosById(shelfPositionId).orElseThrow(()-> new RuntimeException("Shelf position not found"));
 
            if(shelfPosition.getShelf() != null){
                throw new RuntimeException("Shelf position already has a shelf assigned");
            }

            shelfPosition.setShelf((ShelfVO) shelf);
            shelfPositionRepository.save((ShelfPositionVO) shelfPosition);

            return ResponseEntity.ok("Relationship established succesfully");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    @Transactional(readOnly = true)
    public List<ShelfPositionVO> getAvailableShelfPositions(){
        try {
            return shelfPositionRepository.getAvailableShelfPositions();
        } catch (Exception e) {
            throw new RuntimeException("Unable to fetch available Shelf",e);
        }
    }

    @Transactional
    public ShelfPositionVO editShelfPosition(long id,ShelfPositionVO shelfPosition){
        if(!shelfPositionRepository.shelfPosExistsById(id)){
            throw new RuntimeException("Shelf Position not found");
        }
        try {
            return shelfPositionRepository.updateShelfPosById(id, shelfPosition.getName());
        } catch (Exception e) {
            throw new RuntimeException("Unable to edit",e);
        }
    }

    @Transactional
    public String deleteShelfPosition(long id){
        if(!shelfPositionRepository.existsById(id)){
            throw new RuntimeException("Shelf Position not found");
        }
        try {
            shelfPositionRepository.deleteShelfPosById(id);
            return "Shelf Position deleted of id:" + id;
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete",e);
        }
    }
}
