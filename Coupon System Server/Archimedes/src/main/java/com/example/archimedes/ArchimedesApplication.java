package com.example.archimedes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ArchimedesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArchimedesApplication.class, args);
    System.out.println("Eureka Server is running...");
	}

}
