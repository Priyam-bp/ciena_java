package com.assignmentone.springboot.assignment_one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories("com.assignmentone.springboot.assignment_one.repository")
public class AssignmentOneApplication {
    public static void main(String[] args) {
        SpringApplication.run(AssignmentOneApplication.class, args);
    }
}
