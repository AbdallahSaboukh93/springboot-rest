package com.restfullProject.restfullProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.restfullProject.restfullProject.security.AppProperties;

@SpringBootApplication
public class RestfullProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfullProjectApplication.class, args);
		
	}

	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncode() {
		return new  BCryptPasswordEncoder() ;
		
	}
	
	
	@Bean
	public SpringApplicationContext sprinApplicationContext() {
		return new  SpringApplicationContext() ;
		
	}
	
	@Bean("AppProperties")
	public AppProperties appProperties() {
		return new  AppProperties() ;
		
	}
}
