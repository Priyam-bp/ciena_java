package com.assignmentone.springboot.assignment_one.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignmentone.springboot.assignment_one.model.Device;
import com.assignmentone.springboot.assignment_one.repository.DeviceRepository;

@Service
public class DeviceService implements InventoryService{
   
    @Autowired
    private DeviceRepository deviceRepository;

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
        return deviceRepository.save(checkDevice);
    }
}
