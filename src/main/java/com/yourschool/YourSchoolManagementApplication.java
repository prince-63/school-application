package com.yourschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.yourschool.repository")
@EntityScan("com.yourschool.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class YourSchoolManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(YourSchoolManagementApplication.class, args);
	}
}
