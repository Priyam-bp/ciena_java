package com.assignmentone.springboot.assignment_one.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.assignmentone.springboot.assignment_one.model.ShelfPositionVO;

@Repository
public interface ShelfPositionRepository extends Neo4jRepository<ShelfPositionVO,Long>{

@Query("match (s:ShelfPosition) " +
       "where not exists { " +
       "match (s)<-[:HAS]-(:Device) " +
       "} " +
       "return s")
    List<ShelfPositionVO> getAvailableShelfPositions();
}
