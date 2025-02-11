package com.assignmentone.springboot.assignment_one.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignmentone.springboot.assignment_one.model.Device;
import com.assignmentone.springboot.assignment_one.model.ShelfPositionVO;
import com.assignmentone.springboot.assignment_one.repository.DeviceRepository;
import com.assignmentone.springboot.assignment_one.repository.ShelfPositionRepository;

import jakarta.transaction.Transactional;

@Service
public class DeviceService implements InventoryService{
   
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private ShelfPositionRepository shelfPositionRepository;

    @Override
    public Device saveDevice(Device device){
        return deviceRepository.save(device);
    }

    @Override
    public Device getDevice(Long id) {
        return deviceRepository.findById(id).orElseThrow(()->new RuntimeException("Device not found"));
    }

    @Override
    public String deleteDevice(Long id){
        if(!deviceRepository.existsById(id)){
            throw new RuntimeException("Device not found");
        }
        deviceRepository.deleteById(id);
        return "Device deleted of id:" + id;
    }

    @Override
    public Device updateDevice(Long id, Device device){
        
        Device checkDevice = deviceRepository.findById(id).orElseThrow(() -> new RuntimeException("Device not found"));
        checkDevice.setName(device.getName());
        checkDevice.setDeviceType(device.getDeviceType());
        return deviceRepository.save(checkDevice);
    }
    

    @Override
    public List<Device> getAllDevices(){
        return deviceRepository.findAll();
    }


    @Override
    @Transactional
    public void addShelfPositionToDevice(Long deviceId, Long shelfPositionId) {
        Device device = deviceRepository.findById(deviceId).orElseThrow(() -> new RuntimeException("Device not found"));
        ShelfPositionVO shelfPosition = shelfPositionRepository.findById(shelfPositionId).orElseThrow(() -> new RuntimeException("Shelf Position not found"));

        if(shelfPosition.getDevice() != null){
            throw new RuntimeException("Shelf position already has a Device");
        }
        if(device.getShelfPositions() == null){
            device.setShelfPositions(new HashSet<>());
        }

        shelfPosition.setDevice(device);
        device.getShelfPositions().add(shelfPosition);

        deviceRepository.save(device);
        shelfPositionRepository.save(shelfPosition);
    }
}
