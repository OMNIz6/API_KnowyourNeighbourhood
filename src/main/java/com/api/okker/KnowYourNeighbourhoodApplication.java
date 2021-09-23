package com.api.okker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.api.okker.configuration.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class KnowYourNeighbourhoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnowYourNeighbourhoodApplication.class, args);
	}

}
