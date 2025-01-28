package com.assignmentone.springboot.assignment_one.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.assignmentone.springboot.assignment_one.model.ShelfPostionVO;

@Repository
public interface ShelfPositionRepository extends Neo4jRepository<ShelfPostionVO,Long>{
}
