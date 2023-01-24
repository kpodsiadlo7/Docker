package com.example.FamilyMemberApp.service.config;

import com.example.FamilyMemberApp.service.containers.PostgresTestContainers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class ContainersEnvironment {

    @Container
    public static PostgreSQLContainer postgreSQLContainer = PostgresTestContainers.getInstance();
}
