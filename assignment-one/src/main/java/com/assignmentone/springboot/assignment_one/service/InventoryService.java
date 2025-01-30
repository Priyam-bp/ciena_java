package com.assignmentone.springboot.assignment_one.service;

import java.util.List;

import com.assignmentone.springboot.assignment_one.model.Device;

public interface InventoryService {
    Device saveDevice(Device device);
    Device getDevice(Long id);
    String deleteDevice(Long id);
    Device updateDevice(Long id,Device device);
    void addShelfPositionToDevice(Long deviceId,Long shelfPositionId);
    List<Device> getAllDevices();
}
