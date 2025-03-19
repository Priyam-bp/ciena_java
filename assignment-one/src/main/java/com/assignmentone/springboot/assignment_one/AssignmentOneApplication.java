package com.assignmentone.springboot.assignment_one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@EnableNeo4jRepositories("com.assignmentone.springboot.assignment_one.repository")
public class AssignmentOneApplication {
    public static void main(String[] args) {
        SpringApplication.run(AssignmentOneApplication.class, args);
    }
}
