package com.assignmentone.springboot.assignment_one.service_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.assignmentone.springboot.assignment_one.model.Device;
import com.assignmentone.springboot.assignment_one.model.ShelfPostionVO;
import com.assignmentone.springboot.assignment_one.repository.DeviceRepository;
import com.assignmentone.springboot.assignment_one.service.DeviceService;

@SpringBootTest
public class DeviceServiceTests {

    @Autowired
    private DeviceService deviceService;

    @MockitoBean
    private DeviceRepository deviceRepository;

    private Device device1;
    private Device device2;
    private ShelfPostionVO shelfPosition1;

    @BeforeEach
    public void setUp(){
        device1 = new Device(1L, "device1", "deviceType1");
        device2 = new Device (2L, "device2", "deviceType2");
        shelfPosition1 = new ShelfPostionVO("shelfPosition1");
    }

    @Test
    public void getAllDevicesTest(){
        when(deviceRepository.findAll()).thenReturn(Stream.of(device1,device2).collect(Collectors.toList()));
        assertEquals(2, deviceService.getAllDevices().size());
    }

    @Test
    public void getDeviceById(){
        Long id = 1L;
        when(deviceRepository.findById(id)).thenReturn(Optional.of(device1));
        assertEquals(id, deviceService.getDevice(id).getId());
    }

    @Test
    public void getDeviceByIdNotFound(){
        Long id = 20L;
        when(deviceRepository.findById(id)).thenReturn(Optional.empty());
        assertNull(deviceService.getDevice(id));
    }

    @Test
    public void saveDevice(){
        Device deviceTest = new Device(3L, "device3","type C");
        when(deviceRepository.save(deviceTest)).thenReturn(deviceTest);
        assertEquals(deviceTest, deviceService.saveDevice(deviceTest));
    }

    @Test
    public void deleteUserById(){
        deviceService.deleteDevice(3L);
        verify(deviceRepository,times(1)).deleteById(3L);
    }
    
}
