package com.assignmentone.springboot.assignment_one.controller_tests;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.assignmentone.springboot.assignment_one.AssignmentOneApplication;
import com.assignmentone.springboot.assignment_one.model.Device;
import com.assignmentone.springboot.assignment_one.model.AddShelfPositionRequest;
import com.assignmentone.springboot.assignment_one.service.InventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(
    classes = AssignmentOneApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
public class DeviceControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllDevices() throws Exception {
        
        List<Device> allDevices = Arrays.asList(
            new Device(1L, "device1", "deviceType1"),
            new Device(2L, "device2", "deviceType2")
        );
        when(inventoryService.getAllDevices()).thenReturn(allDevices);

        mockMvc.perform(get("/devices")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("device1"))
                .andExpect(jsonPath("$[0].deviceType").value("deviceType1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("device2"));
    }

    
    @Test
    public void getDevice() throws Exception {
        
        Device device = new Device(1L, "device1", "deviceType1");
        when(inventoryService.getDevice(1L)).thenReturn(device);

        
        mockMvc.perform(get("/devices/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("device1"))
                .andExpect(jsonPath("$.deviceType").value("deviceType1"));
    }

   
    @Test
    public void saveDevice() throws Exception {
        
        Device deviceToSave = new Device(1L, "newDevice", "newType");
        Device savedDevice = new Device(1L, "newDevice", "newType");
        when(inventoryService.saveDevice(any(Device.class))).thenReturn(savedDevice);

        
        mockMvc.perform(post("/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(deviceToSave)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("newDevice"))
                .andExpect(jsonPath("$.deviceType").value("newType"));
    }

    
    @Test
    public void updateDevice() throws Exception {
        
        Device deviceToUpdate = new Device(1L, "updatedDevice", "updatedType");
        when(inventoryService.updateDevice(eq(1L), any(Device.class))).thenReturn(deviceToUpdate);

        
        mockMvc.perform(put("/devices/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(deviceToUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("updatedDevice"))
                .andExpect(jsonPath("$.deviceType").value("updatedType"));
    }

    
    @Test
    public void deleteDevice() throws Exception {
        
        when(inventoryService.deleteDevice(1L)).thenReturn("Device deleted of id:1");

        
        mockMvc.perform(delete("/devices/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Device deleted of id:1"));
    }

    
    @Test
    public void addShelfPositionToDevice() throws Exception {
        
        AddShelfPositionRequest request = new AddShelfPositionRequest(1l,2L);
        doNothing().when(inventoryService).addShelfPositionToDevice(1L, 2L);

       
        mockMvc.perform(post("/devices/addShelfPositionToDevice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
        
        verify(inventoryService).addShelfPositionToDevice(1L, 2L);
    }
}