package com.microservice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer // Habilitamos config server en este ms
@SpringBootApplication
public class MicroserviceConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceConfigApplication.class, args);
	}

}
