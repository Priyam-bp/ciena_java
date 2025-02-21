package com.assignmentone.springboot.assignment_one.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.assignmentone.springboot.assignment_one.model.Device;

public interface InventoryService {
    Device saveDevice(Device device);
    Device getDevice(Long id);
    String deleteDevice(Long id);
    Device updateDevice(Long id,Device device);
    ResponseEntity<String> addShelfPositionToDevice(Long deviceId,Long shelfPositionId);
    List<Device> getAllDevices();
}
