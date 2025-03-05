package com.assignmentone.springboot.assignment_one.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignmentone.springboot.assignment_one.model.Device;
import com.assignmentone.springboot.assignment_one.model.ShelfPositionVO;
import com.assignmentone.springboot.assignment_one.model.ShelfVO;
import com.assignmentone.springboot.assignment_one.repository.DeviceRepository;
import com.assignmentone.springboot.assignment_one.repository.ShelfPositionRepository;
import com.assignmentone.springboot.assignment_one.repository.ShelfRepository;

import jakarta.transaction.Transactional;

@Service
public class DeviceService implements InventoryService{
   
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private ShelfPositionRepository shelfPositionRepository;

    @Autowired
    private ShelfRepository shelfRepository;

    @Override
    public Device saveDevice(Device device){
        try {
            return deviceRepository.save(device);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add device",e);
        }
    }

    @Override
    public Device getDevice(Long id) {
        return deviceRepository.getDeviceById(id).orElseThrow(()->new RuntimeException("Device not found"));
    }

    @Override
    public String deleteDevice(Long id){
        if(!deviceRepository.deviceExistsById(id)){
            throw new RuntimeException("Device not found");
        }
        deviceRepository.deleteDeviceById(id);
        return "Device deleted of id:" + id;
    }

    @Override
    @Transactional
    public Device updateDevice(Long id, Device device){
        if(!deviceRepository.deviceExistsById(id)){
            throw new RuntimeException("Device not found");
        } 
        try {
            return deviceRepository.updateDevice(id, device.getName(), device.getDeviceType());
        } catch (Exception e) {
            throw new RuntimeException("Unable to update device",e);
        }
    }
    

    @Override
    public List<Device> getAllDevices(){
        try {
            return deviceRepository.getAllDevices();
        } catch (Exception e) {
            throw new RuntimeException("Unable to fetch",e);
        }
    }


    @Override
    @Transactional
    public ResponseEntity<String> addShelfPositionToDevice(Long deviceId, Long shelfPositionId) {
        try {
            Device device = deviceRepository.findById(deviceId).orElseThrow(() -> new RuntimeException("Device not found"));
            ShelfPositionVO shelfPosition = shelfPositionRepository.findById(shelfPositionId).orElseThrow(() -> new RuntimeException("Shelf Position not found"));

            if (shelfPosition.getDevice() != null) {
                throw new RuntimeException("Shelf position already has a Device");
            }

            if(device.getShelfPositions() == null){
                device.setShelfPositions(new HashSet<>());
            }

            shelfPosition.setDevice(device);
            device.getShelfPositions().add(shelfPosition);

            deviceRepository.save(device);
            shelfPositionRepository.save(shelfPosition);

            return ResponseEntity.ok("Relationship Established");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
