package com.ToDoTracker.SpringClouldApiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin(origins = "http://localhost:4200")
@Configuration
@EnableDiscoveryClient
@SpringBootApplication
public class SpringClouldApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringClouldApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/api/v1/**").uri("http://localhost:8083")) // use the name of the application in the uri

				.route(p -> p.path("/api/v2/**").uri("http://localhost:8084"))
//
				.route(p -> p.path("/api/v3/**").uri("http://localhost:8081"))
//
				.route(p -> p.path("/api/v4/**").uri("http://localhost:8086"))

				.build();
	}

}
