package com.assignmentone.springboot.assignment_one.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignmentone.springboot.assignment_one.model.Device;
import com.assignmentone.springboot.assignment_one.model.ShelfPostionVO;
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
        return deviceRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteDevice(Long id){
        deviceRepository.deleteById(id);
        return "Device deleted of id:" + id;
    }

    @Override
    public Device updateDevice(Long id, Device device){
        
        Device checkDevice = deviceRepository.findById(id).orElse(null);
        if(checkDevice != null){
            checkDevice.setName(device.getName());
            checkDevice.setDeviceType(device.getDeviceType());
        }
        if (checkDevice != null) {
            return deviceRepository.save(checkDevice);
        }
        return null;
    }

    @Override
    public List<Device> getAllDevices(){
        return deviceRepository.findAll();
    }


    @Override
    @Transactional
    public void addShelfPositionToDevice(Long deviceId, Long shelfPositionId) {
        Optional<Device> deviceOptional = deviceRepository.findById(deviceId);
        Optional<ShelfPostionVO> shelfPositionOptional = shelfPositionRepository.findById(shelfPositionId);

        if (deviceOptional.isPresent() && shelfPositionOptional.isPresent()) {
            Device device = deviceOptional.get();
            ShelfPostionVO shelfPostionVO = shelfPositionOptional.get();

            if (device.getShelfPositions() == null) {
                device.setShelfPosition(new HashSet<>());
            }

            if(shelfPostionVO.getDevices() == null){
                shelfPostionVO.setDevices(new HashSet<>());
            }

            device.getShelfPositions().add(shelfPostionVO);
            shelfPostionVO.getDevices().add(device);

            deviceRepository.save(device);
            shelfPositionRepository.save(shelfPostionVO);
        }
    }
}
