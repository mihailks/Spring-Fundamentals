package com.softuni.mobilelele;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@OpenAPIDefinition(
		info = @io.swagger.v3.oas.annotations.info.Info(
				title = "Mobilelele",
				version = "0.1.0",
				description = "Mobilelele REST API"
		),
		servers = {
				@Server(
						url = "http://localhost:8080",
						description = "Local server"
				)
		}
)
@EnableScheduling
@SpringBootApplication
public class MobiLeLeLeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobiLeLeLeApplication.class, args);
	}

}
