package com.assignmentone.springboot.assignment_one.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.assignmentone.springboot.assignment_one.model.Device;
import com.assignmentone.springboot.assignment_one.model.DeviceDTO;

public interface DeviceRepository extends Neo4jRepository<Device,Long>{

    //Get all devices
    @Query("match (device:Device) where device.active = true\r\n" + //
                "optional match (device)-[:HAS]->(s:ShelfPosition)\r\n" + //
                "where s.active = true\r\n" + //
                "return device, ID(s) as shelfPositionid, s.name as shelfPositionname")
    List<DeviceDTO> getAllDevices();

    //Save device
    @Query("merge (d:Device {name: $name, deviceType: $deviceType,}) return d")
    Device saveDevice(@Param("name") String name,@Param("deviceType") String deviceType);

    //find device by id
    @Query("match (device:Device) where ID(device) = $id and device.active = true\r\n" + //
            "optional match (device)-[:HAS]->(shelfPosition:ShelfPosition)\r\n" + //
            "return device, ID(shelfPosition) as shelfPositionid, shelfPosition.name as shelfPositionname")
    Optional<DeviceDTO> getDeviceById(@Param("id") Long id);

    //delete by id
    @Query("match (d:Device)\r\n" + //
                "where ID(d) = $id and d.active = true\r\n" + //
                "match (d)-[r:HAS]->(sh:ShelfPosition)\r\n" + //
                "delete r\r\n" + //
                "set d.active = false\r\n" + //
                "return d.name")
    Optional<String> deleteDeviceById(@Param("id") Long id);

    //check if device exisits and is active
    @Query("match (d:Device) where ID(d) = $id and d.active = true return d is not null as Res")
    Boolean deviceExistsById(@Param("id") Long id);

    //update device
    @Query("match (d:Device) where ID(d) = $id and d.active = true set d.name = $name set d.deviceType = $deviceType return d")
    Device updateDevice(@Param("id") Long id,@Param("name") String name, @Param("deviceType") String deviceType);

    @Query("match (d:Device) where ID(d) = $deviceId and d.active = true match (sp:ShelfPosition) where ID(sp) = $shelfPositionId and sp.active = true merge (d)-[:HAS]->(sp) return d")
    Optional<Device> deviceHasShelfPosition(@Param("deviceId") Long deviceId,@Param("shelfPositionId") Long shelfPositionId);
}
