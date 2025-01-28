package com.assignmentone.springboot.assignment_one.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.assignmentone.springboot.assignment_one.model.AddShelfPositionRequest;
import com.assignmentone.springboot.assignment_one.model.Device;
import com.assignmentone.springboot.assignment_one.repository.DeviceRepository;
import com.assignmentone.springboot.assignment_one.service.InventoryService;

@RestController
@RequestMapping("/devices") // Base URL for all endpoints
public class DeviceController {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    public Device saveDevice(@RequestBody Device device) {
        return inventoryService.saveDevice(device);
    }

    @GetMapping("/{id}")
    public Device getDevice(@PathVariable Long id) {
        return inventoryService.getDevice(id);
    }

    @GetMapping
    public Iterable<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public String deleteDevice(@PathVariable Long id){
        return inventoryService.deleteDevice(id);
    }

    @PutMapping("/{id}")
    public Device updatDevice(@PathVariable Long id, @RequestBody Device device){
        return inventoryService.updateDevice(id, device);
    }

    @PostMapping("/addShelfPositionToDevice")
    public void addShelfPositionToDevice(@RequestBody AddShelfPositionRequest data){
        inventoryService.addShelfPositionToDevice(data.getDeviceId(), data.getshelfPositionId());
    }
}
