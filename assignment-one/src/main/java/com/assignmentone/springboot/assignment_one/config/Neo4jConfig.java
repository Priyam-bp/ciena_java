package com.assignmentone.springboot.assignment_one.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class Neo4jConfig {

    // Neo4jTransactionManager will be automatically created by Spring Boot if Neo4j is configured.
}
