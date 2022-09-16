package com.gft.starter.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gft.starter.gateway.config.filter.AuthFilter;

@Configuration
public class SpringCloudConfig {

	@Autowired
	private AuthFilter filter;
	
	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		 return builder.routes()
					 .route(r -> r.path("/auth/**")
							 .filters(f -> f.filter(filter))
		                	.uri("http://localhost:8082/"))	                		
				 			//.id("auth"))	   
					 
				 	.route(r -> r.path("/nutritionist/**")
				 			.filters(f -> f.filter(filter))
	                		.uri("http://localhost:8083/"))	                		
				 			//.id("nutritionist"))	                
				 	
				 	.route(r -> r.path("/user/**")
				 			.filters(f -> f.filter(filter))
				 			.uri("http://localhost:8084/"))
	                		//.id("user"))
	                
	                .route(r -> r.path("/bookkeeper/**")
	                		.filters(f -> f.filter(filter))
	                		.uri("http://localhost:8085/"))
	                		//.id("bookkeeper"))
	                .build();
	}
}
