package com.oasis.taskmanagement;

import com.oasis.taskmanagement.repositories.custom.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SuppressWarnings({"PMD", "checkstyle:hideutilityclassconstructor"})
@SpringBootApplication
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@EnableScheduling
public class OasisTaskManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(OasisTaskManagementApplication.class, args);
    }

}
