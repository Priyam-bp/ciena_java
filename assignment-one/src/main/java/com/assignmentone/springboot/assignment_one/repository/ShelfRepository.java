package com.assignmentone.springboot.assignment_one.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.assignmentone.springboot.assignment_one.model.ShelfDTO;
import com.assignmentone.springboot.assignment_one.model.ShelfVO;

public interface ShelfRepository extends Neo4jRepository<ShelfVO,Long>{

    @Query("match (s:Shelf)\r\n" + //
                "where not exists {\r\n" + //
                "    match (s)<-[:HAS]-(:ShelfPosition)\r\n" + //
                "}\r\n" + //
                "return s")
    List<ShelfVO> getAvailableShelves();
    
    @Query("match (s:Shelf) where s.active = true \r\n" + //
                "match (s)-[:HAS]->(shelfPosition:ShelfPosition)\r\n" + //
                "where shelfPosition.active = true and not exists {\r\n" + //
                "    match (shelfPosition)<-[:HAS]-(d:Device)\r\n" + //
                "}\r\n" + //
                "return s as shelf,ID(shelfPosition) as shelfPositionId, shelfPosition.name as shelfPositionName")
    List<ShelfDTO> getShelfWithShelfPositons();

    //delete shelf by id and remove shelfPosition's relationship and free up devices 
    @Query("match (s:Shelf) \r\n" + //
                "where ID(s) = $id and s.active = true\r\n" + //
                "optional match (s)-[r:HAS]->(sh:ShelfPosition)\r\n" + //
                "optional match (sh)<-[r1:HAS]-(d:Device)\r\n" + //
                "delete r\r\n" + //
                "delete r1\r\n" + //
                "set s.active = false\r\n" + //
                "set sh.active = false \r\n" + //
                "return \"deleted\"")
    Optional<Boolean> deleteShelfById(@Param("id") Long id);

    //get all shelves
    @Query("match (s:Shelf)\r\n" + //
                "where s.active = true\r\n" + //
                "return s as shelf")
    List<ShelfVO> getAllShelf();

    //update shelf 
    @Query("match (s:Shelf)\r\n" + //
                "where ID(s) = $id and s.active = true\r\n" + //
                "set s.name = $name\r\n" + //
                "set s.shelfType = $shelfType\r\n" + //
                "return s as shelf")
    ShelfVO editShelf(@Param("id") Long id,@Param("name") String name,@Param("shelfType") String shelfType);

    @Query("match (s:Shelf)\r\n" + //
                "where ID(s) = $id and s.active = true\r\n" + //
                "return s is not null as value")
    Boolean findShelfById(@Param("id") Long id);


    //assignment 4 custom query: fetch shelf summary
    @Query("match (shelf:Shelf)-[:HAS]->(shelfPosition:ShelfPosition)\r\n" + //
                "where ID(shelf) = $id and shelf.active = true and shelfPosition.active = true\r\n" + //
                "optional match (shelfPosition)<-[:HAS]-(device:Device)\r\n" + //
                "where device.active = true\r\n" + //
                "return shelf,collect(shelfPosition) as shelfPositions,collect(device) as devices")
    Optional<Map<String,Object>> shelfSummary(@Param("id") Long id);
}
