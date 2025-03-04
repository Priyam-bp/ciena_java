package com.assignmentone.springboot.assignment_one.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignmentone.springboot.assignment_one.model.ShelfPositionVO;

@Repository
public interface ShelfPositionRepository extends Neo4jRepository<ShelfPositionVO,Long>{

    //get available shelf position
    @Query("match (s:ShelfPosition) " +
            "where not exists { " +
            "match (s)<-[:HAS]-(:Device) " +
            "} " +
            "return s")
    List<ShelfPositionVO> getAvailableShelfPositions();

    //get shelf position by id
    @Query("match (s:ShelfPosition)\r\n" + //
                "where ID(s) = $id and s.active = true\r\n" + //
                "return s")
    Optional<ShelfPositionVO> getShelfPosById(@Param("id") Long id); 

    //get all shelf positions
    @Query("match (s:ShelfPosition) where s.active = true return s")
    List<ShelfPositionVO> getAllShelfPos();

    @Query("match (s:ShelfPosition) where ID(s) = $id and s.active = true \r\n" + //
                "set s.name = $name\r\n" + //
                "return s")
    ShelfPositionVO updateShelfPosById(@Param("id") Long id,@Param("name") String name);

    //check if shelf pos exists by id
    @Query("match (s:ShelfPosition) where ID(s) = $id and s.active = true return s is not null as res")
    Boolean shelfPosExistsById(@Param("id") Long id);

    //delete shelf pos by id
    @Query("match (s:ShelfPosition) where ID(s) = $id and s.active = true set s.active = false return \"deleted\"")
    String deleteShelfPosById(@Param("id") Long id);
}
