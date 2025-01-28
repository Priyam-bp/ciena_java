package com.assignmentone.springboot.assignment_one.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.assignmentone.springboot.assignment_one.model.ShelfVO;

public interface ShelfRepository extends Neo4jRepository<ShelfVO,Long>{
}
