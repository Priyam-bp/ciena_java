package com.assignmentone.springboot.assignment_one.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignmentone.springboot.assignment_one.model.Device;
import com.assignmentone.springboot.assignment_one.model.DeviceDTO;
import com.assignmentone.springboot.assignment_one.model.ShelfPositionVO;
import com.assignmentone.springboot.assignment_one.repository.DeviceRepository;
import com.assignmentone.springboot.assignment_one.repository.ShelfPositionRepository;
// import com.assignmentone.springboot.assignment_one.repository.ShelfRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class DeviceService implements InventoryService{
   
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private ShelfPositionRepository shelfPositionRepository;

    // @Autowired
    // private ShelfRepository shelfRepository;

    @Override
    @Transactional
    @CacheEvict(value = "devices", allEntries = true, beforeInvocation = true)
    public Device saveDevice(Device device){
        try {
            return deviceRepository.save(device);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add device",e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "devices", key = "#id")
    public Device getDevice(Long id) {
        try {
            Optional<DeviceDTO> res = deviceRepository.getDeviceById(id);
            if(res == null || !res.isPresent()){
                throw new RuntimeException("Device not found");
            }
            Device device = res.get().getDevice();
            if(res.get().getId() != null){
                ShelfPositionVO shelfPosition = new ShelfPositionVO(res.get().getId(),res.get().getName());
                device.addShelfPosition(shelfPosition);
            }
            System.out.println("afterrr"+device);
            return device;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }    
    }


    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "devices")
    public List<Device> getAllDevices(){
        try {
            List<DeviceDTO> res = deviceRepository.getAllDevices();
            List<Device> deviceList = new ArrayList<>();
            if(res != null && !res.isEmpty()){
                for(DeviceDTO resItem: res){
                    Device device = resItem.getDevice();
                    ShelfPositionVO shelfPosition = new ShelfPositionVO();
                    System.out.println("CHECKKKKK"+resItem.getName()+resItem.getId());
                    if(resItem.getId() != null){
                        shelfPosition.setName(resItem.getName());
                        shelfPosition.setId(resItem.getId());
                        device.addShelfPosition(shelfPosition);
                    }
                    deviceList.add(device);
                }
            }
            return deviceList;
        } catch (Exception e) {
            throw new RuntimeException("Unable to fetch",e);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "devices", allEntries = true)
    public String deleteDevice(Long id){
        if(!deviceRepository.deviceExistsById(id)){
            throw new RuntimeException("Device not found");
        }
        Optional<String> name = deviceRepository.deleteDeviceById(id);
        return "Device deleted of id:" + name;
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
    @Transactional
    public ResponseEntity<String> addShelfPositionToDevice(Long deviceId, Long shelfPositionId) {
        try {
            Device device = deviceRepository.findById(deviceId).orElseThrow(() -> new RuntimeException("Device not found"));
            ShelfPositionVO shelfPosition = shelfPositionRepository.findById(shelfPositionId).orElseThrow(() -> new RuntimeException("Shelf Position not found"));

            if(!deviceRepository.checkShelf(shelfPositionId)){
                throw new RuntimeException("Shelf Position is not associated to a shelf");
            }

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
