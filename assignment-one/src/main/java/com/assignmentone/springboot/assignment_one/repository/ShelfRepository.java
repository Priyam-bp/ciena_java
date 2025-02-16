package com.assignmentone.springboot.assignment_one.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.assignmentone.springboot.assignment_one.model.ShelfVO;

public interface ShelfRepository extends Neo4jRepository<ShelfVO,Long>{

    @Query("match (s:Shelf)\r\n" + //
                "where not exists {\r\n" + //
                "    match (s)<-[:HAS]-(:ShelfPosition)\r\n" + //
                "}\r\n" + //
                "return s")
    List<ShelfVO> getAvailableShelves();
}
