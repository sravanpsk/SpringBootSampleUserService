package com.sravan.userservice;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringBootWebServiceApplication {
	private static Logger log = Logger.getLogger(SpringBootWebServiceApplication.class);
	public static void main(String[] args) {
		log.info("Logger enabled: Entering main \n\n");	
		SpringApplication.run(SpringBootWebServiceApplication.class, args);
		log.info("Exiting main");
	}
}
