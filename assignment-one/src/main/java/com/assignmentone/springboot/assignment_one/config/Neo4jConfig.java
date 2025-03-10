package com.assignmentone.springboot.assignment_one.config;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class Neo4jConfig {

    @Bean
    public Driver neo4jDriver(){
        return GraphDatabase.driver("bolt://localhost:7687",AuthTokens.basic("neo4j", "password1"));
    }
}
